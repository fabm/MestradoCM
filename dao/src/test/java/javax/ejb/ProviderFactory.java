package javax.ejb;


public interface ProviderFactory {
    <T> com.google.inject.Provider<T> resolve(Class<T> type);
}
