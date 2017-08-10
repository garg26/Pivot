package simplifii.framework.rest.response.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by aman on 21/02/17.
 */

public class IdentityDoc implements Serializable{

    @SerializedName("docType")
    @Expose
    private String docType;
    @SerializedName("nameOnDoc")
    @Expose
    private String nameOnDoc;
    @SerializedName("docNumber")
    @Expose
    private String docNumber;
    @SerializedName("docUrl")
    @Expose
    private String docUrl;

//    private String docType;
//    private String nameOnDoc;
//    private String docNumber;
//    private String docUrl;
    private String docName;

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getNameOnDoc() {
        return nameOnDoc;
    }
    public void setNameOnDoc(String nameOnDoc) {
        this.nameOnDoc = nameOnDoc;
    }
    public String getDocNumber() {
        return docNumber;
    }
    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }
    public String getDocUrl() {
        return docUrl;
    }
    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }
}
