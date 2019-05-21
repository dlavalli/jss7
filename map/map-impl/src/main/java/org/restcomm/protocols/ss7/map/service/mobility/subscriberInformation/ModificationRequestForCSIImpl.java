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

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ModificationRequestForCSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AdditionalRequestedCAMELSubscriptionInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedCAMELSubscriptionInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ModificationInstruction;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

public class ModificationRequestForCSIImpl extends SequenceBase implements ModificationRequestForCSI {

    private static final int _TAG_requested_CAMEL_subscription_info = 1;
    private static final int _TAG_MODIF_NOTIF_TO_CSE = 2;
    private static final int _TAG_MODIF_CSI_STATE = 3;
    private static final int _TAG_EXTENSION_CONTAINER = 4;
    private static final int _TAG_additional_requested_CAMEL_subscription_info = 5;

    private RequestedCAMELSubscriptionInfo requestedCAMELSubscriptionInfo;
    private ModificationInstruction modifyNotificationToCSE;
    private ModificationInstruction modificationCSIState;
    private MAPExtensionContainer extensionContainer;
    private AdditionalRequestedCAMELSubscriptionInfo additionalRequestedCAMELSubscriptionInfo;

    public ModificationRequestForCSIImpl() {
        super("ModificationRequestForCSIImpl");
    }

    public ModificationRequestForCSIImpl(RequestedCAMELSubscriptionInfo requestedCAMELSubscriptionInfo,
                                         ModificationInstruction modifyNotificationToCSE,
                                         ModificationInstruction modificationCSIState,
                                         MAPExtensionContainer extensionContainer,
                                         AdditionalRequestedCAMELSubscriptionInfo additionalRequestedCAMELSubscriptionInfo) {
        super("ModificationRequestForCSIImpl");

        this.requestedCAMELSubscriptionInfo = requestedCAMELSubscriptionInfo;
        this.modifyNotificationToCSE = modifyNotificationToCSE;
        this.modificationCSIState = modificationCSIState;
        this.extensionContainer = extensionContainer;
        this.additionalRequestedCAMELSubscriptionInfo = additionalRequestedCAMELSubscriptionInfo;
    }

    public RequestedCAMELSubscriptionInfo getRequestedCamelSubscriptionInfo() { // Mandatory
        return this.requestedCAMELSubscriptionInfo;
    }

    public ModificationInstruction getModifyNotificationToCSE() {
        return this.modifyNotificationToCSE;
    }

    public ModificationInstruction getModifyCSIState() {
        return this.modificationCSIState;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public AdditionalRequestedCAMELSubscriptionInfo getAdditionalRequestedCamelSubscriptionInfo() {
        return this.additionalRequestedCAMELSubscriptionInfo;
    }
    
    @Override
    protected void _decode(AsnInputStream asnIS, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.requestedCAMELSubscriptionInfo = null;
        this.modifyNotificationToCSE = null;
        this.modificationCSIState = null;
        this.extensionContainer = null;
        this.additionalRequestedCAMELSubscriptionInfo = null;

        AsnInputStream ais = ansIS.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _TAG_requested_CAMEL_subscription_info:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                    "Error while decoding RequestedInfo: Parameter requestedCAMELSubscriptionInfo is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        int requestedInfo = (int)ais.readInteger();
                        this.requestedCAMELSubscriptionInfo = RequestedCAMELSubscriptionInfo.getInstance(requestedInfo);
                        break;

                    case _TAG_MODIF_NOTIF_TO_CSE:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter modifyNotificationToCSE is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.modifyNotificationToCSE = new ModificationInstruction();
                        ((ModificationInstruction)this.modifyNotificationToCSE).decodeAll(ai
                        break;

                    case _TAG_MODIF_CSI_STATE:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter modificationCSIState is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.modificationCSIState = new ModificationInstruction();
                        ((ModificationInstruction)this.modificationCSIState).decodeAll(ai
                        break;

                    case _TAG_EXTENSION_CONTAINER:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter extensionContainer is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl)this.extensionContainer).decodeAll(ais);
                        break;

                    case _TAG_additional_requested_CAMEL_subscription_info:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                    "Error while decoding RequestedInfo: Parameter additionalRequestedCAMELSubscriptionInfo is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        int requestedAdditionalInfo = (int)ais.readInteger();
                        this.additionalRequestedCAMELSubscriptionInfo = AdditionalRequestedCAMELSubscriptionInfo.getInstance(requestedAdditionalInfo);
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.requestedCAMELSubscriptionInfo == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": requestedCAMELSubscriptionInfo parameter is mandatory but is not found",
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
        if (this.requestedCAMELSubscriptionInfo == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter requestedCAMELSubscriptionInfo is not defined");
        }

        if (this.requestedCAMELSubscriptionInfo != null) {
            asnOs.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_requested_CAMEL_subscription_info, requestedCAMELSubscriptionInfo.getCode());
        }

        if (this.modifyNotificationToCSE != null) {
            ((ModificationInstruction)this.modifyNotificationToCSE).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_MODIF_NOTIF_TO_CSE);
        }

        if (this.modificationCSIState != null) {
            ((ModificationInstruction)this.modificationCSIState).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_MODIF_CSI_STATE);
        }

        if (this.extensionContainer != null) {
            ((MAPExtensionContainerImpl)this.extensionContainer).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_EXTENSION_CONTAINER);
        }

        if (this.additionalRequestedCAMELSubscriptionInfo != null) {
            asnOs.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_additional_requested_CAMEL_subscription_info, additionalRequestedCAMELSubscriptionInfo.getCode());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.requestedCAMELSubscriptionInfo != null) {
            sb.append("requestedCAMELSubscriptionInfo=");
            sb.append(this.requestedCAMELSubscriptionInfo);
        }
        if (this.modifyNotificationToCSE != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("modifyNotificationToCSE=");
            sb.append(this.modifyNotificationToCSE);
        }
        if (this.modificationCSIState  != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("modificationCSIState=");
            sb.append(this.modificationCSIState );
        }
        if (this.extensionContainer  != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer);
        }
        if (this.additionalRequestedCAMELSubscriptionInfo  != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("additionalRequestedCAMELSubscriptionInfo=");
            sb.append(this.additionalRequestedCAMELSubscriptionInfo);
        }

        sb.append("]");
        return sb.toString();
    }
}

