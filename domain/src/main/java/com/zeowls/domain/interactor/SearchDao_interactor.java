package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Search;
import com.zeowls.domain.executor.ThreadTransformer;
import com.zeowls.domain.interactor.useCases.SearchCase;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ViewScope;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

@ViewScope
public class SearchDao_interactor extends SearchCase<List<Search>> {
    private CompositeDisposable disposables;
    private Repository repository;
    private ThreadTransformer threadTransformer;

    @Inject
    public SearchDao_interactor(Repository repository, ThreadTransformer threadTransformer) {
        this.repository = repository;
        this.threadTransformer = threadTransformer;
        this.disposables = new CompositeDisposable();
    }

    public void recent(Consumer<? super List<Search>> onSuccess, Consumer<? super Throwable> onError) {
        disposables.add(repository.getRecentSearch().compose(threadTransformer).subscribe(onSuccess, onError));
    }

    public void suggestions(Consumer<? super List<Search>> onSuccess, Consumer<? super Throwable> onError) {
        disposables.add(repository.getLocalSuggestion().compose(threadTransformer).subscribe(onSuccess, onError));
    }

    public Completable insert(String name) {
        return repository.addRecent(name).compose(threadTransformer);
    }

    public Completable deleteRecent(int id) {
        return repository.deleteRecent(id).compose(threadTransformer);
    }

    @Override
    public Completable deleteAllRecent() {
        return repository.deleteAllRecent().compose(threadTransformer);
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.clear();
        }
    }
}
