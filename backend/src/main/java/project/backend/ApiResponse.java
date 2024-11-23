package project.backend;

public class ApiResponse<T>
{
    private String message;
    private T data;

    public ApiResponse(String message, T data)
    {
        this.message = message;
        this.data = data;
    }
    public String getMessage()
    {
        return this.message;
    }
    public T getData()
    {
        return this.data;
    }
    public void setMessage(String message)
    {
        this.message= message;
    }
    public void setData(T data)
    {
        this.data =data;
    }
}