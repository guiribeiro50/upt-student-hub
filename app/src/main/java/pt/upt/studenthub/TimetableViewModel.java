package pt.upt.studenthub;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimetableViewModel extends ViewModel {
    private static final long DELAY_MS = 800;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final MutableLiveData<SyncState> state = new MutableLiveData<>(SyncState.IDLE);
    private final MutableLiveData<Integer> progress = new MutableLiveData<>(0);
    private final MutableLiveData<List<String>> sessions =
            new MutableLiveData<>(Collections.<String>emptyList());

    public LiveData<SyncState> getState() {
        return state;
    }

    public LiveData<Integer> getProgress() {
        return progress;
    }

    public LiveData<List<String>> getSessions() {
        return sessions;
    }
}
