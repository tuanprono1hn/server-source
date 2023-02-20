//package vn.telsoft.acm.util;
//
//import org.primefaces.context.RequestContext;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//
//public class PrimefacesUtil {
//    public static void addSuccessMessage(String summary) {
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Thông báo", summary));
//    }
//
//    public static void addErrorMessage(String summary) {
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lỗi", summary));
//    }
//
//    public static void showDialog(String dialogId) {
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("PF('"+dialogId+"').show();");
//    }
//
//    public static void hideDialog(String dialogId) {
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("PF('"+dialogId+"').hide();");
//    }
//}
