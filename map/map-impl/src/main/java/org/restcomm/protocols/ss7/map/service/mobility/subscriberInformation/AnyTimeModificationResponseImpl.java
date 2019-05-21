/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2016, Telestax Inc and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeModificationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CAMELSubscriptionInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallBarringData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallForwardingData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallHoldData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallWaitingData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ClipData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ClirData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.EctData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSISDNBS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ODBInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CSGSubscriptionData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.AddressStringImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.CSGSubscriptionDataImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.OfferedCamel4CSIsImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.SupportedCamelPhasesImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ODBInfoImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.CallWaitingDataImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.CallHoldDataImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ClipDataImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ClirDataImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.EctDataImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ExtSSInfoForCSEImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.CAMELSubscriptionInfoImpl;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dlavalli on 10/08/18.
 */
public class AnyTimeModificationResponseImpl extends MobilityMessageImpl implements AnyTimeModificationResponse, MAPAsnPrimitive {

    private static final int _TAG_SS-INFOFOR-CSE = 1;
    private static final int _TAG_CAMEL-SUBSCRIPTIONINFO = 2;
    private static final int _TAG_EXTENSIONCONTAINER = 3;
    private static final int _TAG_ODB-INFO = 4;
    private static final int _TAG_CW-DATA = 5;
    private static final int _TAG_CH-DATA = 6;
    private static final int _TAG_CLIP-DATA = 7;
    private static final int _TAG_CLIR-DATA = 8;
    private static final int _TAG_ECT-DATA = 9;
    private static final int _TAG_SERVICECENTREADDRESS = 10;

    public static final String _PrimitiveName = "AnyTimeModificationResponse";

    private ExtSSInfoForCSE ssInfoForCSE;
    private CAMELSubscriptionInfo camelSubscriptionInfo;
    private ODBInfo odbInfo;
    private CallWaitingData callWaitingData;
    private CallHoldData callHoldData;
    private ClipData clipData;
    private ClirData clirData;
    private EctData ectData;
    private AddressString serviceCentreAddress;
    private MAPExtensionContainer extensionContainer;

    public AnyTimeModificationResponseImpl() {
        super();
    }

    public AnyTimeModificationResponseImpl(ExtSSInfoForCSE ssInfoForCSE, CAMELSubscriptionInfo camelSubscriptionInfo, ODBInfo odbInfo,
                                           CallWaitingData callWaitingData, CallHoldData callHoldData, ClipData clipData, ClirData clirData,
                                           EctData ectData, AddressString serviceCentreAddress, MAPExtensionContainer extensionContainer) {
        super();

        this.ssInfoForCSE = ssInfoForCSE;
        this.camelSubscriptionInfo = camelSubscriptionInfo;
        this.odbInfo = odbInfo;
        this.callWaitingData = callWaitingData;
        this.callHoldData = callHoldData;
        this.clipData = clipData;
        this.clirData = clirData;
        this.ectData = ectData;
        this.serviceCentreAddress = serviceCentreAddress;
        this.extensionContainer = extensionContainer;
    }

    public ExtSSInfoForCSE getSsInfoForCSE() {
        return ssInfoForCSE;
    }

    public CAMELSubscriptionInfo getCamelSubscriptionInfo() { 
        return camelSubscriptionInfo;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    public ODBInfo getOdbInfo() { 
        return odbInfo;
    }

    public CallWaitingData getCwData() { 
        return callWaitingData;
    }

    public CallHoldData getChData() { 
        return callHoldData;
    }

    public ClipData getClipData() { 
        return clipData;
    }

    public ClirData getClirData() { 
        return clirData;
    }

    public EctData getEctData() {
        return ectData;
    }

    public AddressString getServiceCentreAddress() {
        return serviceCentreAddress;
    }

    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return false;
    }

    public void decodeAll(AsnInputStream ansIS) throws MAPParsingComponentException {
        try {
            int length = ansIS.readLength();
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream ansIS, int length) throws MAPParsingComponentException {
        try {
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream ansIS, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.ssInfoForCSE = null;
        this.camelSubscriptionInfo = null;
        this.odbInfo = null;
        this.callWaitingData = null;
        this.callHoldData = null;
        this.clipData = null;
        this.clirData = null;
        this.ectData = null;
        this.serviceCentreAddress = null;
        this.extensionContainer = null;

        AsnInputStream ais = ansIS.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _TAG_SS-INFOFOR-CSE:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter ssInfoForCSE is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.ssInfoForCSE = new ExtSSInfoForCSEImpl();
                        ((ExtSSInfoForCSEImpl)this.ssInfoForCSE).decodeAll(ais);
                        break;

                    case _TAG_CAMEL-SUBSCRIPTIONINFO:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter camelSubscriptionInfo is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.camelSubscriptionInfo = new CAMELSubscriptionInfoImpl();
                        ((CAMELSubscriptionInfoImpl)this.camelSubscriptionInfo).decodeAll(ais);
                        break;

                    case _TAG_ODB-INFO:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter odbInfo is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.odbInfo = new ODBInfoImpl();
                        ((ODBInfoImpl)this.odbInfo).decodeAll(ais);
                        break;

                    case _TAG_CW-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter cwData is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.cwData = new CallWaitingDataImpl();
                        ((CallWaitingDataImpl)this.cwData).decodeAll(ais);
                        break;

                    case _TAG_CH-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter chData is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.chData = new CallHoldDataImpl();
                        ((CallHoldDataImpl)this.chData).decodeAll(ais);
                        break;

                    case _TAG_CLIP-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter clipData is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.clipData = new ClipDataImpl();
                        ((ClipDataImpl)this.clipData).decodeAll(ais);
                        break;

                    case _TAG_CLIR-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter clirData is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.clirData = new ClirDataImpl();
                        ((ClirDataImpl)this.clirData).decodeAll(ais);
                        break;

                    case _TAG_ECT-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter imsi is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.ectData = new EctDataImpl();
                        ((EctDataImpl)this.ectData).decodeAll(ais);
                        break;

                    case _TAG_SERVICECENTREADDRESS:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter serviceCentreAddress is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.serviceCentreAddress = new AddressStringImpl();
                        ((AddressStringImpl)this.serviceCentreAddress).decodeAll(ais);
                        break;

                    case _TAG_EXTENSIONCONTAINER:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter extensionContainer is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl)this.extensionContainer).decodeAll(ais);
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

                
    }

    public void encodeAll(AsnOutputStream asnOs) throws MAPException {
        this.encodeAll(asnOs, this.getTagClass(), this.getTag());
    }

    public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws MAPException {
        try {
            asnOs.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOs.StartContentDefiniteLength();
            this.encodeData(asnOs);
            asnOs.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + " : " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOs) throws MAPException {
        
        if (this.ssInfoForCSE != null) {
            ((ExtSSInfoForCSEImpl)this.ssInfoForCSE).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_SS-INFOFOR-CSE);
        }
        if (this.camelSubscriptionInfo != null) {
            ((CAMELSubscriptionInfoImpl)this.camelSubscriptionInfo).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_CAMEL-SUBSCRIPTIONINFO);
        }
        if (this.odbInfo != null) {
            ((ODBInfoImpl)this.odbInfo).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_ODB-INFO);
        }
        if (this.callWaitingData != null) {
            ((CallWaitingDataImpl)this.callWaitingData).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_CW-DATA);
        }
        if (this.callHoldData != null) {
            ((CallHoldDataImpl)this.callHoldData).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_CH-DATA);
        }
        if (this.clipData != null) {
            ((ClipDataImpl)this.clipData).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_CLIP-DATA);
        }
        if (this.clirData != null) {
            ((ClirDataImpl)this.clirData).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_CLIR-DATA);
        }
        if (this.ectData != null) {
            ((EctDataImpl)this.ectData).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_ECT-DATA);
        }
        if (this.serviceCentreAddress != null) {
            ((AddressStringImpl)this.serviceCentreAddress).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_SERVICECENTREADDRESS);
        }
        if (this.extensionContainer != null) {
            ((MAPExtensionContainerImpl)this.extensionContainer).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_EXTENSIONCONTAINER);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.MAPMessage#getMessageType()
     */
    public MAPMessageType getMessageType() {
        return MAPMessageType.anyTimeModification_Response;
    }

    public int getOperationCode() {
        return MAPOperationCode.anyTimeModification;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");


        if (this.ssInfoForCSE != null) {
            sb.append("ssInfoForCSE=");
            sb.append(this.ssInfoForCSE);
        }
        if (this.camelSubscriptionInfo != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("camelSubscriptionInfo=");
            sb.append(this.camelSubscriptionInfo);
        }
        if (this.odbInfo != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("odbInfo=");
            sb.append(this.odbInfo);
        }
        if (this.callWaitingData != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("callWaitingData=");
            sb.append(this.callWaitingData);
        }
        if (this.callHoldData != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("callHoldData=");
            sb.append(this.callHoldData);
        }
        if (this.clipData != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("clipData=");
            sb.append(this.clipData);
        }
        if (this.clirData != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("clirData=");
            sb.append(this.clirData);
        }
        if (this.ectData != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("ectData=");
            sb.append(this.ectData);
        }
        if (this.serviceCentreAddress != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("serviceCentreAddress=");
            sb.append(this.serviceCentreAddress);
        }
        if (this.extensionContainer != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer);
        }


        sb.append("]");
        return sb.toString();
    }
}

