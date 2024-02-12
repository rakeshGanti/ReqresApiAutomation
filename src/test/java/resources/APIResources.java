package resources;

public enum APIResources {
    GetUserList("/api/users?page=2"),
    GetSingleUser("/api/users/2"),
    GetUserNotFound("/api/users/23"),
    AddUserApi("/api/users"),
    UpdateUserApi("/api/users/2"),
    DeletePlaceAPI("/api/users/2");
    private String resource;

    APIResources(String resource)
    {
        this.resource=resource;
    }

    public String getResource()
    {
        return resource;
    }
}
