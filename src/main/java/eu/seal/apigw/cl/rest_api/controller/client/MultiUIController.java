package eu.seal.apigw.cl.rest_api.controller.client;
/**
Copyright © 2019  Atos Spain SA. All rights reserved.
This file is part of XXXX.
XXXX is free software: you can redistribute it and/or modify it under the terms of #license#.
THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT ANY WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT, IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
See README file for the full disclaimer information and LICENSE file for full license information in the project root.
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class MultiUIController
{
    @GetMapping("idpRedirection")
    public String getIdpRedirection(HttpSession session, Model model) throws Exception
    {
        System.out.println("En MultiUIController.getIdpRedirection");

        return "idpRedirect";
    }

    @GetMapping("client")
    public String getHtmlForm(HttpSession session, Model model) throws Exception
    {
    	
    	String urlReturn = (String) session.getAttribute("urlReturn");
        String urlFinishProcess = (String) session.getAttribute("urlFinishProcess");
        String infoMessage = (String) session.getAttribute("infoMessage");
        String errorMessage = (String) session.getAttribute("errorMessage");
        String SPName = (String) session.getAttribute("SPName");
        String privacyPolicy = (String) session.getAttribute("privacyPolicy");
        String consentQuery = (String) session.getAttribute("consentQuery");
        String consentFinish = (String) session.getAttribute("consentFinish");
        
    	/*
        EntityMetadataList apsList = (EntityMetadataList) session.getAttribute("apsList");
        AttributeTypeList attributesRequestList = (AttributeTypeList) session
                .getAttribute("attributesRequestList");
        AttributeTypeList attributesSendList = (AttributeTypeList) session
                .getAttribute("attributesSendList");
        AttributeSetList attributesConsentList = (AttributeSetList) session
                .getAttribute("attributesConsentList");
        

        if (apsList == null || attributesRequestList == null || urlReturn == null
                || urlFinishProcess == null)
        {
            throw new Exception("Data not initialize");
        }

        List<APClient> apClientList = new ArrayList<APClient>();

        for (int i = 0; i < apsList.size(); i++)
        {
            APClient apClient = APClient.getAPClientFrom(apsList.get(i), i);
            apClientList.add(apClient);
        }

        model.addAttribute("apsList", apClientList);

        List<AttributeClient> attributeClientList = new ArrayList<AttributeClient>();

        for (int i = 0; i < attributesRequestList.size(); i++)
        {
            AttributeClient attributeClient = AttributeClient
                    .getAttributeClientFrom(attributesRequestList.get(i), i);
            attributeClientList.add(attributeClient);
        }

        model.addAttribute("attributesRequestList", attributeClientList);

        List<AttributeClient> attributeClientSendList = new ArrayList<AttributeClient>();

        if (attributesSendList != null)
        {
            for (int i = 0; i < attributesSendList.size(); i++)
            {
                AttributeClient attributeClient = AttributeClient
                        .getAttributeClientFrom(attributesSendList.get(i), i);
                attributeClientSendList.add(attributeClient);
            }
        }

        model.addAttribute("attributesSendList", attributeClientSendList);

        List<AttributeSetClient> consentList = new ArrayList<AttributeSetClient>();

        if (attributesConsentList != null)
        {
            for (AttributeSet attributeSet : attributesConsentList)
            {
                AttributeSetClient attributeSetClient = new AttributeSetClient();
                attributeSetClient.setId(attributeSet.getIssuer());

                List<AttributeClient> aux = new ArrayList<AttributeClient>();
                if (attributeSet.getAttributes() != null)
                {
                    for (int i = 0; i < attributeSet.getAttributes().size(); i++)
                    {
                        AttributeClient attributeClient = AttributeClient
                                .getAttributeClientFrom(attributeSet.getAttributes().get(i), i);
                        aux.add(attributeClient);
                    }

                    attributeSetClient.setAttributeClientList(aux);
                }
                consentList.add(attributeSetClient);
            }
        }

        model.addAttribute("attributesConsentList", consentList);
*/
        model.addAttribute("urlFinishProcess", urlFinishProcess);

        if (infoMessage != null)
        {
            model.addAttribute("infoMessage", infoMessage);
        }
        if (errorMessage != null)
        {
            model.addAttribute("errorMessage", errorMessage);
        }

        model.addAttribute("SPName", (SPName != null) ? SPName : "Service Provider");
        model.addAttribute("privacyPolicy", privacyPolicy);
        model.addAttribute("consentQuery", consentQuery);
        model.addAttribute("consentFinish", consentFinish);

    	System.out.println("Showing results ...");
        return "form";
    }

    @GetMapping("client/init")
    public String initSessionParams(HttpSession session)
    {
/*
        EntityMetadataList apsList = new EntityMetadataList();

        EntityMetadata ap1 = new EntityMetadata();
        ap1.setEntityId("https://esmo.uji.es/gw/saml/idp/metadata.xml");
        ap1.setDefaultDisplayName("UJI AP Provider");
        ap1.setLogo(
                "data:image/png;base64,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");
        apsList.add(ap1);

        EntityMetadata ap2 = new EntityMetadata();
        ap2.setEntityId("https://esmo2.uji.es/gw/saml/idp/metadata.xml");
        ap2.setDefaultDisplayName("AP Service 2");
        apsList.add(ap2);

        EntityMetadata ap3 = new EntityMetadata();
        ap3.setEntityId("https://esmo3.uji.es/gw/saml/idp/metadata.xml");
        ap3.setDefaultDisplayName("AP Service 3");
        apsList.add(ap3);

        EntityMetadata ap4 = new EntityMetadata();
        ap4.setEntityId("https://esmo4.uji.es/gw/saml/idp/metadata.xml");
        ap4.setDefaultDisplayName("AP Service 4");
        apsList.add(ap4);

        session.setAttribute("apsList", apsList);

        AttributeTypeList attributeList = new AttributeTypeList();

        AttributeType attr1 = new AttributeType();
        attr1.setName("studies");
        attr1.setFriendlyName("Studies degree");
        attributeList.add(attr1);

        AttributeType attr2 = new AttributeType();
        attr2.setName("course");
        // attr1.setFriendlyName("Studies degree");
        attributeList.add(attr2);

        AttributeType attr3 = new AttributeType();
        attr3.setName("studentNumber");
        attr3.setFriendlyName("Student number");
        attributeList.add(attr3);

        session.setAttribute("attributesRequestList", attributeList);

        AttributeTypeList attributeSendList = new AttributeTypeList();

        AttributeType attrSend1 = new AttributeType();
        attrSend1.setName("name");
        attrSend1.setFriendlyName("Full name");
        attrSend1.setValues(Arrays.asList("Pepe Botella"));
        attributeSendList.add(attrSend1);

        AttributeType attrSend2 = new AttributeType();
        attrSend2.setName("idNumber");
        attrSend2.setFriendlyName("Identification number");
        attrSend2.setValues(Arrays.asList("123456"));
        attributeSendList.add(attrSend2);

        AttributeType attrSend3 = new AttributeType();
        attrSend3.setName("age");
        attrSend3.setValues(Arrays.asList("25"));
        attributeSendList.add(attrSend3);

        session.setAttribute("attributesSendList", attributeSendList);

        

        // Recolected consent attributes

        AttributeSetList consentList = new AttributeSetList();

        AttributeSet attributeSet1 = new AttributeSet();
        attributeSet1.setIssuer("http://attr-set-1-id.com");

        List<AttributeType> attributeConsentList1 = new ArrayList<AttributeType>();

        AttributeType attrCons1 = new AttributeType();
        attrCons1.setName("studies");
        attrCons1.setFriendlyName("Studies degree");
        attrCons1.setValues(Arrays.asList("Computer degree"));
        attributeConsentList1.add(attrCons1);

        AttributeType attrCons2 = new AttributeType();
        attrCons2.setName("course");
        attrCons2.setValues(Arrays.asList("1", "2"));
        attributeConsentList1.add(attrCons2);

        attributeSet1.setAttributes(attributeConsentList1);
        consentList.add(attributeSet1);

        AttributeSet attributeSet2 = new AttributeSet();
        attributeSet2.setIssuer("http://attr-set-2-id.com");

        List<AttributeType> attributeConsentList2 = new ArrayList<AttributeType>();

        AttributeType attrCons3 = new AttributeType();
        attrCons3.setName("studies");
        attrCons3.setFriendlyName("Studies degree");
        attrCons3.setValues(Arrays.asList("Mathematics degree"));
        attributeConsentList2.add(attrCons3);

        AttributeType attrCons4 = new AttributeType();
        attrCons4.setName("course");
        attrCons4.setValues(Arrays.asList("2"));
        attributeConsentList2.add(attrCons4);

        attributeSet2.setAttributes(attributeConsentList2);
        consentList.add(attributeSet2);

        session.setAttribute("attributesConsentList", consentList);
*/
    	
    	
    	session.setAttribute("urlReturn", "client/return");
        session.setAttribute("urlFinishProcess", "client/finish");
        
        session.setAttribute("errorMessage", "An error has occurred");
        session.setAttribute("infoMessage", "This is an information message");

        session.setAttribute("privacyPolicy", "http://www.esmo-project.eu/privacy-policy");
        session.setAttribute("consentQuery", "Click to consent to the above data query and to receive requested data." +
                " You are accepting the privacy policy conditions, please be sure you understand them.");
        

        return "redirect:../client";
    }

    @PostMapping("client")
    public String getRequest(@RequestBody MultiValueMap<String, String> formData,
            HttpSession session, Model model)
    {
/*
        int apIndex = Integer.parseInt(formData.get("apId").get(0));
        String[] attrRequestList = formData.get("attrRequestList").get(0).split(",");
        String[] attrSendList = formData.get("attrSendList").get(0).split(",");
        String attrConsent = formData.get("attrConsentList").get(0);

        EntityMetadataList apsList = (EntityMetadataList) session.getAttribute("apsList");
        AttributeTypeList attributesRequestList = (AttributeTypeList) session
                .getAttribute("attributesRequestList");
        AttributeTypeList attributesSendList = (AttributeTypeList) session
                .getAttribute("attributesSendList");
        AttributeSetList attributesConsentList = (AttributeSetList) session
                .getAttribute("attributesConsentList");

        String urlReturn = (String) session.getAttribute("urlReturn");

        EntityMetadataList apsListNew = new EntityMetadataList();
        try
        {
            apsListNew.add(apsList.get(apIndex));
        }
        catch (Exception e)
        {
        }

        AttributeTypeList attributesRequestListNew = new AttributeTypeList();
        for (String index : attrRequestList)
        {
            try
            {
                attributesRequestListNew.add(attributesRequestList.get(Integer.parseInt(index)));
            }
            catch (Exception e)
            {
            }
        }

        AttributeTypeList attributesSendListNew = new AttributeTypeList();
        for (String index : attrSendList)
        {
            try
            {
                attributesSendListNew.add(attributesSendList.get(Integer.parseInt(index)));
            }
            catch (Exception e)
            {
            }
        }

        AttributeSetList attributesConsentListNew = new AttributeSetList();

        if (attrConsent != null && !attrConsent.equals(""))
        {
            String[] attributeSets = attrConsent.split("#");
            for (String attributeSet : attributeSets)
            {
                String[] aux = attributeSet.split(":");
                String id = aux[0];
                String[] indexes = aux[1].split(",");
                AttributeSet consentNew = null;

                for (AttributeSet consent : attributesConsentList)
                {
                    if (consent.getId().equals(id))
                    {
                        consentNew = consent;
                        List<AttributeType> attrs = new ArrayList<AttributeType>();
                        for (String index : indexes)
                        {
                            attrs.add(consent.getAttributes().get(Integer.parseInt(index)));
                        }
                        consentNew.setAttributes(attrs);
                        break;
                    }
                }
                if (consentNew != null)
                {
                    attributesConsentListNew.add(consentNew);
                }
            }
        }

        session.setAttribute("apsList", apsListNew);
        session.setAttribute("attributesRequestList", attributesRequestListNew);
        session.setAttribute("attributesSendList", attributesSendListNew);
        session.setAttribute("attributesConsentList", attributesConsentListNew);
*/
		String urlReturn = (String) session.getAttribute("urlReturn");
        System.out.println("||||| urlReturn:" + urlReturn);
        System.out.println("||||| sessionId:" + session.getAttribute("sessionId"));
        session.setAttribute("sessionId", session.getAttribute("sessionId"));

    	
    	System.out.println("Returning ...");
        return "redirect:" + urlReturn;
    }
}
