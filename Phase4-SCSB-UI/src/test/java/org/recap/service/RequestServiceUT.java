package org.recap.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.Assert;
import org.codehaus.jettison.json.JSONException;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.recap.BaseTestCase;
import org.recap.RecapConstants;
import org.recap.controller.RequestController;
import org.recap.model.jpa.*;
import org.recap.model.search.RequestForm;
import org.recap.model.usermanagement.UserDetailsForm;
import org.recap.repository.jpa.*;
import org.recap.util.RequestServiceUtil;
import org.recap.util.UserAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by akulak on 24/4/17.
 */
public class RequestServiceUT extends BaseTestCase {

    @PersistenceContext
    private EntityManager entityManager;

    @Mock
    RequestService requestServiceMocked;

    @Autowired
    RequestService requestService;

    @Mock
    javax.servlet.http.HttpServletRequest request;

    @Mock
    BindingAwareModelMap model;

    @Mock
    HttpSession session;

    @Mock
    BindingResult bindingResult;

    @Mock
    RequestServiceUtil requestServiceUtil;

    @Mock
    private UserAuthUtil userAuthUtil;

    @Mock
    RequestController requestController;

    @Autowired
    CustomerCodeDetailsRepository customerCodeDetailsRepository;

    @Mock
    CustomerCodeDetailsRepository mockedCustomerCodeDetailsRepository;

    @Mock
    InstitutionDetailsRepository institutionDetailsRepository;

    @Mock
    RequestItemDetailsRepository requestItemDetailsRepository;

    @Mock
    ItemDetailsRepository itemDetailsRepository;

    @Mock
    RequestTypeDetailsRepository requestTypeDetailsRepository;

    @Mock
    RequestStatusDetailsRepository requestStatusDetailsRepository;

    public BindingAwareModelMap getModel() {
        return model;
    }

    @Test
    public void testDeliveryLocations() throws Exception{
        RequestForm requestForm = getRequestForm();
        ItemEntity itemEntity = getItemEntity();
        UserDetailsForm userDetailsForm = getUserDetailsForm(false);
        Map<String, String> deliveryLocationsMap = new HashMap<>();
        requestService.processCustomerAndDeliveryCodes(requestForm,deliveryLocationsMap,userDetailsForm,itemEntity,1);
        List<String> deliveryLocationList = new ArrayList<>();
        for(String deliveryLocation : deliveryLocationsMap.keySet()){
            deliveryLocationList.add(deliveryLocation);
        }
        CustomerCodeEntity CustomerCode = customerCodeDetailsRepository.findByCustomerCode(itemEntity.getCustomerCode());
        String deliveryRestrictions = CustomerCode.getDeliveryRestrictions();
        String[] splitDeliveryLocation = StringUtils.split(deliveryRestrictions, ",");
        String[] deliveryRestrictionsArray = Arrays.stream(splitDeliveryLocation).map(String::trim).toArray(String[]::new);
        assertTrue(deliveryLocationList.containsAll(Arrays.asList(deliveryRestrictionsArray)) && Arrays.asList(deliveryRestrictionsArray).containsAll(deliveryLocationList));
    }

    @Test
    public void checkGetterServices(){
        Mockito.when(requestServiceMocked.getRequestItemDetailsRepository()).thenCallRealMethod();
        Mockito.when(requestServiceMocked.getRequestServiceUtil()).thenCallRealMethod();
        Mockito.when(requestServiceMocked.getUserAuthUtil()).thenCallRealMethod();
        Mockito.when(requestServiceMocked.getInstitutionDetailsRepository()).thenCallRealMethod();
        Mockito.when(requestServiceMocked.getRequestTypeDetailsRepository()).thenCallRealMethod();
        Mockito.when(requestServiceMocked.getCustomerCodeDetailsRepository()).thenCallRealMethod();
        Mockito.when(requestServiceMocked.getItemDetailsRepository()).thenCallRealMethod();
        Mockito.when(requestServiceMocked.getRequestService()).thenCallRealMethod();
        Mockito.when(requestServiceMocked.getRequestStatusDetailsRepository()).thenCallRealMethod();

        assertNotEquals(requestServiceMocked.getRequestItemDetailsRepository(),requestItemDetailsRepository);
        assertNotEquals(requestServiceMocked.getItemDetailsRepository(),itemDetailsRepository);
        assertNotEquals(requestServiceMocked.getCustomerCodeDetailsRepository(),mockedCustomerCodeDetailsRepository);
        assertNotEquals(requestServiceMocked.getRequestStatusDetailsRepository(),requestStatusDetailsRepository);
        assertNotEquals(requestServiceMocked.getInstitutionDetailsRepository(),institutionDetailRepository);
        assertNotEquals(requestServiceMocked.getRequestTypeDetailsRepository(),requestTypeDetailsRepository);
        assertNotEquals(requestServiceMocked.getRequestService(),requestServiceMocked);
        assertNotEquals(requestServiceMocked.getRequestServiceUtil(),requestServiceUtil);
        assertNotEquals(requestServiceMocked.getUserAuthUtil(),userAuthUtil);

    }

    @Test
    public void testDeliveryLocationsForRecapUser() throws Exception{
        RequestForm requestForm = getRequestForm();
        ItemEntity itemEntity = getItemEntity();
        UserDetailsForm userDetailsForm = getUserDetailsForm(true);
        Map<String, String> deliveryLocationsMap = new HashMap<>();
        requestService.processCustomerAndDeliveryCodes(requestForm,deliveryLocationsMap,userDetailsForm,itemEntity,1);
        List<String> deliveryLocationList = new ArrayList<>();
        for(String deliveryLocation : deliveryLocationsMap.keySet()){
            deliveryLocationList.add(deliveryLocation);
        }
        CustomerCodeEntity customerCode = customerCodeDetailsRepository.findByCustomerCode(itemEntity.getCustomerCode());
        String deliveryRestrictions = customerCode.getDeliveryRestrictions();
        String recapDeliveryRestrictions = customerCode.getRecapDeliveryRestrictions();
        String[] deliveryRestrictionSplit = StringUtils.split(deliveryRestrictions, ",");
        String[] recapDeliveryLocationSplit = StringUtils.split(recapDeliveryRestrictions, ",");
        String[] deliveryRestrictionsArray = Arrays.stream(deliveryRestrictionSplit).map(String::trim).toArray(String[]::new);
        String[] recapDeliveryRestrictionsArray = Arrays.stream(recapDeliveryLocationSplit).map(String::trim).toArray(String[]::new);
        List<String> deliveryLocationsList= new ArrayList<>(Arrays.asList(deliveryRestrictionsArray));
        deliveryLocationsList.addAll(Arrays.asList(recapDeliveryRestrictionsArray));
        assertTrue(deliveryLocationList.containsAll(deliveryLocationsList) && deliveryLocationsList.containsAll(deliveryLocationList));
    }

    @Test
    public void populateItem() throws Exception {
        RequestForm requestForm = new RequestForm();
        BibliographicEntity bibliographicEntity = saveBibSingleHoldingsSingleItem();
        String barcode = bibliographicEntity.getItemEntities().get(0).getBarcode();
        requestForm.setItemBarcodeInRequest(barcode);
        Mockito.when(requestServiceMocked.getItemDetailsRepository()).thenReturn(itemDetailsRepository);
        Mockito.when(requestServiceMocked.getUserAuthUtil()).thenReturn(userAuthUtil);
        when(request.getSession()).thenReturn(session);
        UserDetailsForm userDetailsForm = getUserDetails();
        Mockito.when(requestServiceMocked.populateItemForRequest(requestForm,request)).thenCallRealMethod();
        Mockito.when(requestServiceMocked.getItemDetailsRepository()).thenReturn(itemDetailsRepository);
        List<ItemEntity> itemEntityList=new ArrayList<>();
        ItemEntity itemEntity=new ItemEntity();
        itemEntity.setCustomerCode("PA");
        itemEntityList.add(itemEntity);
        Mockito.when(requestServiceMocked.getRequestTypeDetailsRepository()).thenReturn(requestTypeDetailsRepository);
        Mockito.when(requestServiceMocked.getCustomerCodeDetailsRepository()).thenReturn(mockedCustomerCodeDetailsRepository);

        List<RequestTypeEntity> requestTypeEntityList=new ArrayList<>();
        RequestTypeEntity requestTypeEntity = new RequestTypeEntity();
        requestTypeEntity.setRequestTypeCode("RETRIEVAL");
        requestTypeEntity.setRequestTypeDesc("RETRIEVAL");
        requestTypeEntity.setRequestTypeId(1);
        requestTypeEntityList.add(requestTypeEntity);
        Mockito.doCallRealMethod().when(requestServiceMocked).populateItemForRequest(requestForm,request);
        String response = requestServiceMocked.populateItemForRequest(requestForm,request);
        assertNotNull(response);
    }

    @Test
    public void testRefreshingStatus(){
        String status="status[]";
        String[] statusValue={"16-0"};
        MockHttpServletRequest mockedRequest = new MockHttpServletRequest();
        mockedRequest.addParameter(status, statusValue);
        RequestItemEntity requestItemEntity=getRequestItemEntity();
        Mockito.when(requestServiceMocked.getRequestStatusDetailsRepository()).thenReturn(requestStatusDetailsRepository);
        Mockito.when(requestServiceMocked.getRequestItemDetailsRepository()).thenReturn(requestItemDetailsRepository);
        Mockito.when(requestServiceMocked.getRequestStatusDetailsRepository().findAllRequestStatusDescExceptProcessing()).thenReturn(Arrays.asList("RETRIEVAL ORDER PLACED","RECALL ORDER PLACED","EDD ORDER PLACED","REFILED","CANCELED","EXCEPTION","PENDING","INITIAL LOAD"));
        Mockito.when(requestItemDetailsRepository.findByRequestIdIn(Arrays.asList(requestItemEntity.getRequestId()))).thenReturn(Arrays.asList(requestItemEntity));
        Mockito.when(requestServiceMocked.getRefreshedStatus(mockedRequest)).thenCallRealMethod();
        String refreshedStatus = requestServiceMocked.getRefreshedStatus(mockedRequest);
        assertNotNull(refreshedStatus);
    }

    @Test
    public void testSetFormDetailsForRequest() throws JSONException {
        UserDetailsForm userDetailsForm = getUserDetailsForm();
        Mockito.when(requestServiceMocked.getInstitutionDetailsRepository()).thenReturn(institutionDetailRepository);
        Mockito.when(requestServiceMocked.getRequestTypeDetailsRepository()).thenReturn(requestTypeDetailsRepository);
        Object barcode="123";
        Mockito.when(((BindingAwareModelMap) model).get(RecapConstants.REQUESTED_BARCODE)).thenReturn(barcode);
        Mockito.when(requestServiceMocked.setFormDetailsForRequest(model,request,userDetailsForm)).thenCallRealMethod();
        Mockito.when(requestServiceMocked.setDefaultsToCreateRequest(userDetailsForm,model)).thenCallRealMethod();
        RequestForm requestForm = requestServiceMocked.setFormDetailsForRequest(model, request, userDetailsForm);
        Assert.notNull(requestForm);
    }

    @Test
    public void testSetDefaultsToCreateRequest(){
        UserDetailsForm userDetailsForm = getUserDetailsForm();
        Mockito.when(requestServiceMocked.getInstitutionDetailsRepository()).thenReturn(institutionDetailRepository);
        Mockito.when(requestServiceMocked.getRequestTypeDetailsRepository()).thenReturn(requestTypeDetailsRepository);
        Mockito.when(requestServiceMocked.setDefaultsToCreateRequest(userDetailsForm,model)).thenCallRealMethod();
        RequestForm requestForm = requestServiceMocked.setDefaultsToCreateRequest(userDetailsForm, model);
        Assert.notNull(requestForm);
    }

    @Test
    public void testFindAllRequestStatusExceptProcessing(){
        List<RequestStatusEntity> allExceptProcessing = getRequestStatusEntityList();
        Mockito.when(requestServiceMocked.getRequestStatusDetailsRepository()).thenReturn(requestStatusDetailsRepository);
        Mockito.when(requestStatusDetailsRepository.findAllExceptProcessing()).thenReturn(allExceptProcessing);
        List<String> requestStatuses=new ArrayList<>();
        Mockito.doCallRealMethod().when(requestServiceMocked).findAllRequestStatusExceptProcessing(requestStatuses);
        requestServiceMocked.findAllRequestStatusExceptProcessing(requestStatuses);
    }

    @Test
    public void testGetInstitutionForSuperAdmin(){
        Mockito.when(requestServiceMocked.getInstitutionDetailsRepository()).thenReturn(institutionDetailRepository);
        List<String> institutionList=new ArrayList<>();
        Mockito.doCallRealMethod().when(requestServiceMocked).getInstitutionForSuperAdmin(institutionList);
        requestServiceMocked.getInstitutionForSuperAdmin(institutionList);
    }


    public BibliographicEntity saveBibSingleHoldingsSingleItem() throws Exception {
        Random random = new Random();
        BibliographicEntity bibliographicEntity = new BibliographicEntity();
        bibliographicEntity.setContent("mock Content".getBytes());
        bibliographicEntity.setCreatedDate(new Date());
        bibliographicEntity.setLastUpdatedDate(new Date());
        bibliographicEntity.setCreatedBy("tst");
        bibliographicEntity.setLastUpdatedBy("tst");
        bibliographicEntity.setOwningInstitutionId(1);
        bibliographicEntity.setOwningInstitutionBibId(String.valueOf(random.nextInt()));

        HoldingsEntity holdingsEntity = new HoldingsEntity();
        holdingsEntity.setContent("mock holdings".getBytes());
        holdingsEntity.setCreatedDate(new Date());
        holdingsEntity.setLastUpdatedDate(new Date());
        holdingsEntity.setCreatedBy("tst");
        holdingsEntity.setLastUpdatedBy("tst");
        holdingsEntity.setOwningInstitutionId(1);
        holdingsEntity.setOwningInstitutionHoldingsId(String.valueOf(random.nextInt()));

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setLastUpdatedDate(new Date());
        itemEntity.setOwningInstitutionItemId(String.valueOf(random.nextInt()));
        itemEntity.setOwningInstitutionId(1);
        itemEntity.setBarcode("123");
        itemEntity.setCallNumber("x.12321");
        itemEntity.setCollectionGroupId(3);
        itemEntity.setCallNumberType("1");
        itemEntity.setCustomerCode("123");
        itemEntity.setCreatedDate(new Date());
        itemEntity.setCreatedBy("tst");
        itemEntity.setLastUpdatedBy("tst");
        itemEntity.setItemAvailabilityStatusId(1);
        itemEntity.setHoldingsEntities(Arrays.asList(holdingsEntity));
        itemEntity.setBibliographicEntities(Arrays.asList(bibliographicEntity));
        bibliographicEntity.setHoldingsEntities(Arrays.asList(holdingsEntity));
        bibliographicEntity.setItemEntities(Arrays.asList(itemEntity));

        return bibliographicEntity;

    }

    private UserDetailsForm getUserDetails(){
        UserDetailsForm userDetailsForm=new UserDetailsForm();
        userDetailsForm.setLoginInstitutionId(2);
        userDetailsForm.setSuperAdmin(false);
        userDetailsForm.setRecapPermissionAllowed(false);
        userDetailsForm.setRecapUser(false);
        return userDetailsForm;
    }

    private ItemEntity getItemEntity() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setCustomerCode("PA");
        return itemEntity;
    }

    private RequestForm getRequestForm() {
        RequestForm requestForm = new RequestForm();
        requestForm.setItemOwningInstitution("PUL");
        requestForm.setRequestingInstitution("PUL");
        return requestForm;
    }

    private UserDetailsForm getUserDetailsForm(boolean recapUser) {
        UserDetailsForm userDetailsForm = new UserDetailsForm();
        userDetailsForm.setRecapUser(recapUser);
        return userDetailsForm;
    }

    private UserDetailsForm getUserDetailsForm(){
        UserDetailsForm userDetailsForm=new UserDetailsForm();
        userDetailsForm.setLoginInstitutionId(2);
        userDetailsForm.setSuperAdmin(false);
        userDetailsForm.setRecapPermissionAllowed(false);
        userDetailsForm.setRecapUser(false);
        return userDetailsForm;
    }

    private RequestItemEntity getRequestItemEntity(){
        RequestStatusEntity requestStatusEntity=new RequestStatusEntity();
        requestStatusEntity.setRequestStatusDescription("RECALL");
        RequestItemEntity requestItemEntity = new RequestItemEntity();
        requestItemEntity.setRequestStatusId(15);
        requestItemEntity.setRequestId(16);
        requestItemEntity.setRequestStatusEntity(requestStatusEntity);
        return requestItemEntity;
    }

    private List<RequestStatusEntity> getRequestStatusEntityList(){
        List<RequestStatusEntity> allExceptProcessing = new ArrayList<>();
        RequestStatusEntity requestStatusEntity =new RequestStatusEntity();
        requestStatusEntity.setRequestStatusId(1);
        requestStatusEntity.setRequestStatusCode("RETRIEVAL_ORDER_PLACED");
        requestStatusEntity.setRequestStatusDescription("RETRIEVAL ORDER PLACED");
        allExceptProcessing.add(requestStatusEntity);
        RequestStatusEntity requestStatusEntity1 =new RequestStatusEntity();
        requestStatusEntity1.setRequestStatusId(2);
        requestStatusEntity1.setRequestStatusCode("RECALL_ORDER_PLACED");
        requestStatusEntity1.setRequestStatusDescription("RECALL ORDER PLACED");
        allExceptProcessing.add(requestStatusEntity1);
        RequestStatusEntity requestStatusEntity2 =new RequestStatusEntity();
        requestStatusEntity2.setRequestStatusId(3);
        requestStatusEntity2.setRequestStatusCode("EDD_ORDER_PLACED");
        requestStatusEntity2.setRequestStatusDescription("EDD ORDER PLACED");
        allExceptProcessing.add(requestStatusEntity2);
        return allExceptProcessing;
    }
}
