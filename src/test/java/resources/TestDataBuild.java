package resources;

import pojo.AddDataReqres;

public class TestDataBuild {

    public AddDataReqres addReqresPayLoad(String name, String job)
    {
        AddDataReqres data =new AddDataReqres();
        data.setName(name);
        data.setJob(job);
        return data;
    }

}
