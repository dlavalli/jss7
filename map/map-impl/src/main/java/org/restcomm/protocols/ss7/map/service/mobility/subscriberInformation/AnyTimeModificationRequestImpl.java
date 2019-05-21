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
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeModificationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedSubscriptionInfo;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SubscriberIdentityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ModificationRequestForCFInfoImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ModificationRequestForCBInfoImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ModificationRequestForCSIImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ModificationRequestForODBdataImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ModificationRequestForIPSMGWDataImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.RequestedServingNodeImpl;              // TODO
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ModificationRequestForCSGImpl;         // TODO
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ModificationRequestForCWInfoImpl;      // TODO
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ModificationRequestForCLIPInfoImpl;    // TODO
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ModificationRequestForCLIRInfoImpl;    // TODO
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ModificationRequestForCHInfoImpl;      // TODO
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ModificationRequestForECTInfoImpl;     // TODO

import java.io.IOException;

/**
 * Created by dlavalli on 10/08/18.
 */
public class AnyTimeModificationRequestImpl extends MobilityMessageImpl implements AnyTimeModificationRequest, MAPAsnPrimitive {

    private static final int _TAG_SUBSCRIBERIDENTITY = 1;
    private static final int _TAG_GSMSCF-ADDRESS = 2;
    private static final int _TAG_MODIFICATIONREQUESTFOR-CF-INFO = 3;
    private static final int _TAG_MODIFICATIONREQUESTFOR-CB-INFO = 4;
    private static final int _TAG_MODIFICATIONREQUESTFOR-CSI = 5;
    private static final int _TAG_EXTENSIONCONTAINER = 6;
    private static final int _TAG_LONGFTN-SUPPORTED = 7;
    private static final int _TAG_MODIFICATIONREQUESTFOR-ODB-DATA = 8;
    private static final int _TAG_MODIFICATIONREQUESTFOR-IP-SM-GW-DATA = 9;
    private static final int _TAG_ACTIVATIONREQUESTFORUE-REACHABILITY = 10;
    private static final int _TAG_MODIFICATIONREQUESTFOR-CSG = 11;
    private static final int _TAG_MODIFICATIONREQUESTFOR-CW-DATA = 12;
    private static final int _TAG_MODIFICATIONREQUESTFOR-CLIP-DATA = 13;
    private static final int _TAG_MODIFICATIONREQUESTFOR-CLIR-DATA = 14;
    private static final int _TAG_MODIFICATIONREQUESTFOR-HOLD-DATA = 15;
    private static final int _TAG_MODIFICATIONREQUESTFOR-ECT-DATA = 16;

    public static final String _PrimitiveName = "AnyTimeModificationRequest";

    private SubscriberIdentity subscriberIdentity;
    private ISDNAddressString gsmSCFAddress;
    private boolean isLongFTNSupported;
    private ModificationRequestForCFInfo mrfCFInfo;
    private ModificationRequestForCBInfo mrfCBInfo;
    private ModificationRequestForCSI mrfCSI;
    private ModificationRequestForODBdata mrfODBdata;
    private ModificationRequestForIPSMGWData mrfIPSMGWData;
    private RequestedServingNode activationReqForUEreachability;
    private ModificationRequestForCSG mrfCSG;
    private ModificationRequestForCWInfo mrfCWInfo;
    private ModificationRequestForCLIPInfo mrfCLIPInfo;
    private ModificationRequestForCLIRInfo mrfCLIRInfo;
    private ModificationRequestForCHInfo mrfCHInfo;
    private ModificationRequestForECTInfo mrfECTInfo;
    private MAPExtensionContainer extensionContainer;


    public AnyTimeModificationRequestImpl() {
        super();
    }

    public AnyTimeModificationRequestImpl(
            SubscriberIdentity subscriberIdentity, ISDNAddressString gsmSCFAddress, boolean isLongFTNSupported,
            ModificationRequestForCFInfo mrfCFInfo, ModificationRequestForCBInfo mrfCBInfo, ModificationRequestForCSI mrfCSI,
            ModificationRequestForODBdata mrfODBdata, ModificationRequestForIPSMGWData mrfIPSMGWData, RequestedServingNode activationReqForUEreachability,
            ModificationRequestForCSG mrfCSG, ModificationRequestForCWInfo mrfCWInfo, ModificationRequestForCLIPInfo mrfCLIPInfo,
            ModificationRequestForCLIRInfo mrfCLIRInfo, ModificationRequestForCHInfo mrfCHInfo, ModificationRequestForECTInfo mrfECTInfo,
            MAPExtensionContainer extensionContainer) {
        super();

        this.subscriberIdentity = subscriberIdentity;
        this.gsmSCFAddress = gsmSCFAddress;
        this.isLongFTNSupported = isLongFTNSupported;
        this.mrfCFInfo = mrfCFInfo;
        this.mrfCBInfo = mrfCBInfo;
        this.mrfCSI = mrfCSI;
        this.mrfODBdata = mrfODBdata;
        this.mrfIPSMGWData = mrfIPSMGWData;
        this.activationReqForUEreachability = activationReqForUEreachability;
        this.mrfCSG = mrfCSG;
        this.mrfCWInfo = mrfCWInfo;
        this.mrfCLIPInfo = mrfCLIPInfo;
        this.mrfCLIRInfo = mrfCLIRInfo;
        this.mrfCHInfo = mrfCHInfo;
        this.mrfECTInfo = mrfECTInfo;
        this.extensionContainer = extensionContainer;
    }

    public SubscriberIdentity getSubscriberIdentity() {
        return subscriberIdentity;
    }

    public ISDNAddressString getGsmSCFAddress() {
        return gsmSCFAddress;
    }

    public ModificationRequestForCFInfo getModificationRequestForCfInfo() {
        return mrfCFInfo;
    }

    public ModificationRequestForCBInfo getModificationRequestForCbInfo() {
        return mrfCBInfo;
    }

    public ModificationRequestForCSI getModificationRequestForCSI() {
        return mrfCSI;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    public boolean getLongFTNSupported() {
        return isLongFTNSupported;
    }

    public ModificationRequestForODBdata getModificationRequestForODBdata() {
        return mrfODBdata;
    }

    public ModificationRequestForIPSMGWData getModificationRequestForIpSmGwData() {
        return mrfIPSMGWData;
    }

    public RequestedServingNode getActivationRequestForUEReachability() {
        return activationReqForUEreachability;
    }

    public ModificationRequestForCSG getModificationRequestForCSG() {
        return mrfCSG;
    }

    public ModificationRequestForCWInfo getModificationRequestForCwData() {
        return mrfCWInfo;
    }

    public ModificationRequestForCLIPInfo getModificationRequestForClipData(){
        return mrfCLIPInfo;
    }

    public ModificationRequestForCLIRInfo getModificationRequestForClirData() {
        return mrfCLIRInfo;
    }

    public ModificationRequestForCHInfo getModificationRequestForHoldData() {
        return mrfECTInfo;
    }

    public ModificationRequestForECTInfo getModificationRequestForEctData() {
        return mrfECTInfo;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.MAPMessage#getMessageType()
     */
    public MAPMessageType getMessageType() {
        return MAPMessageType.anyTimeModification_Request;
    }

    public int getOperationCode() {
        return MAPOperationCode.anyTimeModification;
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
        AsnInputStream ais = ansIS.readSequenceStreamData(length);

        this.subscriberIdentity = null;
        this.gsmSCFAddress = null;
        this.isLongFTNSupported = false;
        this.mrfCFInfo = null;
        this.mrfCBInfo = null;
        this.mrfCSI = null;
        this.mrfODBdata = null;
        this.mrfIPSMGWData = null;
        this.activationReqForUEreachability = null;
        this.mrfCSG = null;
        this.mrfCWInfo = null;
        this.mrfCLIPInfo = null;
        this.mrfCLIRInfo = null;
        this.mrfCHInfo = null;
        this.mrfECTInfo = null;
        this.extensionContainer = null;

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _TAG_SUBSCRIBERIDENTITY:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter subscriberIdentity is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.subscriberIdentity = new SubscriberIdentity();
                        AsnInputStream ais2 = ais.readSequenceStream();
                        ais2.readTag();
                        ((SubscriberIdentityImpl) this.subscriberIdentity).decodeAll(ais2);
                        break;

                    case _TAG_GSMSCF-ADDRESS:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter gsmSCFAddress is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.gsmSCFAddress = new ISDNAddressString();
                         ((ISDNAddressStringImpl)this.gsmSCFAddress).decodeAll(ais);
                        break;

                    case _TAG_MODIFICATIONREQUESTFOR-CF-INFO:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter mrfCFInfo is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.mrfCFInfo = new ModificationRequestForCFInfo();
                         ((ModificationRequestForCFInfoImpl)this.mrfCFInfo).decodeAll(ais);a
                        break;

                    case _TAG_MODIFICATIONREQUESTFOR-CB-INFO:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter mrfCBInfo is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.mrfCBInfo = new ModificationRequestForCBInfo();
                         ((ModificationRequestForCBInfoImpl)this.mrfCBInfo).decodeAll(ais);
                        break;

                    case _TAG_MODIFICATIONREQUESTFOR-CSI:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter mrfCSI is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.mrfCSI = new ModificationRequestForCSI();
                         ((ModificationRequestForCSIImpl)this.mrfCSI).decodeAll(ais);
                        break;

                    case _TAG_EXTENSIONCONTAINER:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter extensionContainer is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl)this.extensionContainer).decodeAll(ais);
                        break;

                    case _TAG_LONGFTN-SUPPORTED:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter longFTNSupported is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        ais.readNull();
                        this.isLongFTNSupported = Boolean.TRUE;
                        break

                    case _TAG_MODIFICATIONREQUESTFOR-ODB-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter mrfODBdata is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.mrfODBdata = new ModificationRequestForODBdata();
                        ((ModificationRequestForODBdataImpl)this.mrfODBdata).decodeAll(ais);
                        break;

                    case _TAG_MODIFICATIONREQUESTFOR-IP-SM-GW-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter mrfIPSMGWData is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.mrfIPSMGWData = new ModificationRequestForIPSMGWData();
                        ((ModificationRequestForIPSMGWDataImpl)this.mrfIPSMGWData).decodeAll(ais);
                        break;

                    case _TAG_ACTIVATIONREQUESTFORUE-REACHABILITY:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter activationReqForUEreachability is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.activationReqForUEreachability = new RequestedServingNode();
                        ((RequestedServingNodeImpl)this.activationReqForUEreachability).decodeAll(ais);
                        break;

                    case _TAG_MODIFICATIONREQUESTFOR-CSG:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter mrfCSG is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.mrfCSG = new ModificationRequestForCSG();
                        ((ModificationRequestForCSGImpl)this.mrfCSG).decodeAll(ais);
                        break;

                    case _TAG_MODIFICATIONREQUESTFOR-CW-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter mrfCWInfo is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.mrfCWInfo = new ModificationRequestForCWInfo();
                        ((ModificationRequestForCWInfoImpl)this.mrfCWInfo).decodeAll(ais);
                        break;

                    case _TAG_MODIFICATIONREQUESTFOR-CLIP-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter mrfCLIPInfo is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.mrfCLIPInfo = new ModificationRequestForCLIPInfo();
                        ((ModificationRequestForCLIPInfoImpl)this.mrfCLIPInfo).decodeAll(ais);
                        break;

                    case _TAG_MODIFICATIONREQUESTFOR-CLIR-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter mrfCLIRInfo is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.mrfCLIRInfo = new ModificationRequestForCLIRInfo();
                        ((ModificationRequestForCLIRInfoImpl)this.mrfCLIRInfo).decodeAll(ais);
                        break;

                    case _TAG_MODIFICATIONREQUESTFOR-HOLD-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter mrfCHInfo is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.mrfCHInfo = new ModificationRequestForCHInfo();
                        ((ModificationRequestForCHInfoImpl)this.mrfCHInfo).decodeAll(ais);
                        break;

                    case _TAG_MODIFICATIONREQUESTFOR-ECT-DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter mrfECTInfo is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.mrfECTInfo = new ModificationRequestForECTInfo();
                        ((ModificationRequestForECTInfoImpl)this.mrfECTInfo).decodeAll(ais);
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.subscriberIdentity == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": subscriberIdentity parameter is mandatory but is not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (this.gsmSCFAddress == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": gsmSCFAddress parameter is mandatory but is not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
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
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOs) throws MAPException {
        if (this.subscriberIdentity == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter subscriberIdentity is not defined");
        }

        if (this.gsmSCFAddress == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter gsmSCFAddress is not defined");
        }

        try {
            asnOs.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG_SUBSCRIBER_IDENTITY);
            int pos = asnOs.StartContentDefiniteLength();
            ((SubscriberIdentityImpl) this.subscriberIdentity).encodeAll(asnOs);
            asnOs.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException while encoding " + _PrimitiveName
                    + " parameter subscriberIdentity [0] SubscriberIdentity");
        }

        ((ISDNAddressStringImpl)this.gsmSCFAddress).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_GSM_SCF_ADDRESS);

        if (this.mapExtensionContainer != null) {
            ((MAPExtensionContainerImpl) this.mapExtensionContainer).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_EXTENSION_CONTAINER);
        }

        if (this.isLongFTNSupported) {
            try {
                asnOs.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_LONG_FTN_SUPPORTED);
            } catch (IOException e) {
                throw new MAPException("IOException when encoding parameter longFTNSupported: ", e);
            } catch (AsnException e) {
                throw new MAPException("AsnException when encoding parameter longFTNSupported: ", e);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.subscriberIdentity != null) {
            sb.append("subscriberIdentity=");
            sb.append(this.subscriberIdentity);
        }
        if (this.gsmSCFAddress != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("gsmSCFAddress=");
            sb.append(this.gsmSCFAddress);
        }
        if (this.isLongFTNSupported) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("isLongFTNSupported=");
            sb.append(s.isLongFTNSupported);
        }
        if (this.mrfCFInfo != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("mrfCFInfo=");
            sb.append(this.mrfCFInfo);
        }
        if (this.mrfCBInfo != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("mrfCBInfo=");
            sb.append(this.mrfCBInfo);
        }
        if (this.mrfCSI != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("mrfCSI=");
            sb.append(this.mrfCSI);
        }
        if (this.mrfODBdata != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("mrfODBdata=");
            sb.append(this.mrfODBdata);
        }
        if (this.mrfIPSMGWData != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("mrfIPSMGWData=");
            sb.append(this.mrfIPSMGWData);
        }
        if (this.activationReqForUEreachability != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("activationReqForUEreachability=");
            sb.append(this.activationReqForUEreachability);
        }
        if (this.mrfCSG != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("mrfCSG=");
            sb.append(this.mrfCSG);
        }
        if (this.mrfCWInfo != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("mrfCWInfo=");
            sb.append(this.mrfCWInfo);
        }
        if (this.mrfCLIPInfo != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("mrfCLIPInfo=");
            sb.append(this.mrfCLIPInfo);
        }
        if (this.mrfCLIRInfo != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("mrfCLIRInfo=");
            sb.append(this.mrfCLIRInfo);
        }
        if (this.mrfCHInfo != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("mrfCHInfo=");
            sb.append(this.mrfCHInfo);
        }
        if (this.mrfECTInfo != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("mrfECTInfo=");
            sb.append(this.mrfECTInfo);
        }
        if (this.mapExtensionContainer != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("extensionContainer=");
            sb.append(this.mapExtensionContainer);
        }

        sb.append("]");
        return sb.toString(); 
    }
}

