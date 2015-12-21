package sample.rs.storedoor.network;


public interface ResponseCallback<T> {

    void success(T t);

    void failure(RestError error);
}
