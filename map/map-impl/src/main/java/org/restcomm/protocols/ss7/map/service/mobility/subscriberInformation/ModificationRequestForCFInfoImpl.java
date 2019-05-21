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
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ModificationRequestForCFInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ModificationInstruction;
import org.restcomm.protocols.ss7.map.service.supplementary.SSCodeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtBasicServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtSSStatusImpl;
import org.restcomm.protocols.ss7.map.primitives.AddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

public class ModificationRequestForCFInfoImpl extends SequenceBase implements ModificationRequestForCFInfo {

    private static final int _TAG_SS_CODE = 1;
    private static final int _TAG_EXT_BASIC_SERVICE_CODE = 2;
    private static final int _TAG_EXT_SS_STATUS = 3;
    private static final int _TAG_FORWARDED_TO_NUMBER = 4;
    private static final int _TAG_FORWARDED_TO_SUBADDRESS = 5
    private static final int _TAG_NO_REPLY_CONDITION_TIME = 6;
    private static final int _TAG_MODIF_NOTIF_TO_CSE = 7;
    private static final int _TAG_EXTENSION_CONTAINER = 8;
        
    private SSCode ssCode;
    private ExtBasicServiceCode extBasicServiceCode;
    private ExtSSStatus extSSStatus;
    private AddressString forwardedToNumber;
    private ISDNSubaddressString forwardedToSubaddress;
    private Integer noReplyConditionTime;
    private ModificationInstruction modifyNotificationToCSE;
    private MAPExtensionContainer extensionContainer;

    public ModificationRequestForCFInfoImpl() {
        super("ModificationRequestForCFInfoImpl");
    }

    public ModificationRequestForCFInfoImpl(SSCode ssCode,
                                            ExtBasicServiceCode extBasicServiceCode,
                                            ExtSSStatus extSSStatus,
                                            AddressString forwardedToNumber,
                                            ISDNSubaddressString forwardedToSubaddress,
                                            Integer noReplyConditionTime,
                                            ModificationInstruction modifyNotificationToCSE,
                                            MAPExtensionContainer extensionContainer) {
        super("ModificationRequestForCFInfoImpl");

        this.ssCode = ssCode;
        this.extBasicServiceCode = extBasicServiceCode;
        this.extSSStatus = extSSStatus;
        this.forwardedToNumber = forwardedToNumber;
        this.forwardedToSubaddress = forwardedToSubaddress;
        this.noReplyConditionTime = noReplyConditionTime;
        this.modifyNotificationToCSE = modifyNotificationToCSE;
        this.extensionContainer = extensionContainer;
    }

    public SSCode getSsCode() {  // mandatory
        return this.ssCode;
    }

    public ExtBasicServiceCode getBasicService() {
        return this.extBasicServiceCode;
    }

    public ExtSSStatus getSsStatus() {
        return this.extSSStatus;
    }

    public AddressString getForwardedToNumber() {
        return this.forwardedToNumber;
    }

    public ISDNSubaddressString getForwardedToSubaddress() {
        return this.forwardedToSubaddress;
    }

    public Integer getNoReplyConditionTime() {
        return this.noReplyConditionTime;
    }

    public ModificationInstruction getModifyNotificationToCSE() {
        return this.modifyNotificationToCSE;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    protected void _decode(AsnInputStream asnIS, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.ssCode = null;
        this.extBasicServiceCode = null;
        this.extSSStatus = null;
        this.forwardedToNumber = null;
        this.forwardedToSubaddress = null;
        this.noReplyConditionTime = null;
        this.modifyNotificationToCSE = null;
        this.extensionContainer = null;

        AsnInputStream ais = ansIS.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _TAG_SS_CODE:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter ssCode is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.ssCode = new SSCodeImpl();
                        ((SSCodeImpl)this.ssCode).decodeAll(ais);
                        break;
                    
                    case _TAG_EXT_BASIC_SERVICE_CODE:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter extBasicServiceCode is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
    
                        this.extBasicServiceCode = new ExtBasicServiceCodeImpl();
                        AsnInputStream ais2 = ais.readSequenceStream();
                        ais2.readTag();
                        ((ExtBasicServiceCodeImpl) this.extBasicServiceCode).decodeAll(ais2);
                        break;

                    case _TAG_EXT_SS_STATUS:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter extSSStatus is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.extSSStatus = new ExtSSStatusImpl();
                        ((ExtSSStatusImpl)this.extSSStatus).decodeAll(ais);
                        break;
                    
                    case _TAG_FORWARDED_TO_NUMBER:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter forwardedToNumber is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.forwardedToNumber = new AddressStringImpl();
                        ((AddressStringImpl)this.forwardedToNumber).decodeAll(ais);
                        break;

                    case _TAG_FORWARDED_TO_SUBADDRESS:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " forwardedToSubaddress: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.forwardedToSubaddress = new ISDNAddressStringImpl();
                        ((ISDNAddressStringImpl)this.forwardedToSubaddress).decodeAll(ais);
                        break;

                    case _TAG_NO_REPLY_CONDITION_TIME:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " noReplyConditionTime;: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.noReplyConditionTime; = (int) ais.readInteger();
                        break;

                    case _TAG_MODIF_NOTIF_TO_CSE:   
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter modifyNotificationToCSE is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.modifyNotificationToCSE = new ModificationInstruction();
                        ((ModificationInstruction)this.modifyNotificationToCSE).decodeAll(ais);
                        break;

                    case _TAG_EXTENSION_CONTAINER:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter extensionContainer is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        extensionContainer = new MAPExtensionContainerImpl();
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
    
        if (this.ssCode == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": ssCode parameter is mandatory but is not found",
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
        if (this.ssCode == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter ssCode is not defined");
        }

        if (this.ssCode != null) {
            ((SSCodeImpl)this.ssCode).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_SS_CODE);
        }
        if (this.extBasicServiceCode != null) {
            ((ExtBasicServiceCodeImpl)this.extBasicServiceCode).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_EXT_BASIC_SERVICE_CODE);
        }
        if (this.extSSStatus != null) {
            ((ExtSSStatusImpl)this.extSSStatus).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_EXT_SS_STATUS);
        }
        if (this.forwardedToNumber != null) {
            ((AddressStringImpl)this.forwardedToNumber).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_FORWARDED_TO_NUMBER);
        }
        if (this.forwardedToSubaddress != null) {
            ((ISDNSubaddressStringImpl)this.forwardedToSubaddress).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_FORWARDED_TO_SUBADDRESS);
        }
        if (this.noReplyConditionTime != null) {
            asnOs.writeInteger(this.noReplyConditionTime);
        }
        if (this.modifyNotificationToCSE != null) {
            ((ModificationInstruction)this.modifyNotificationToCSE).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_MODIF_NOTIF_TO_CSE);
        }
        if (this.extensionContainer != null) {
            ((MAPExtensionContainerImpl)this.extensionContainer).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_EXTENSION_CONTAINER);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        
        if (this.ssCode != null) {
            sb.append("ssCode=");
            sb.append(this.ssCode);
        }
        if (this.extBasicServiceCode != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("extBasicServiceCode=");
            sb.append(this.extBasicServiceCode);
        }
        if (this.extSSStatus != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("extSSStatus=");
            sb.append(this.extSSStatus);
        }
        if (this.forwardedToNumber != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("forwardedToNumber=");
            sb.append(this.forwardedToNumber);
        }
        if (this.forwardedToSubaddress != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("forwardedToSubaddress=");
            sb.append(this.forwardedToSubaddress);
        }
        if (this.noReplyConditionTime != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("noReplyConditionTime=");
            sb.append(this.noReplyConditionTime);
        }
        if (this.modifyNotificationToCSE != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("modifyNotificationToCSE=");
            sb.append(this.modifyNotificationToCSE);
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

