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

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBData;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ModificationRequestForODBdata;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ModificationInstruction;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ODBDataImpl;

public class ModificationRequestForODBdataImpl extends SequenceBase implements ModificationRequestForODBdata {

    private static final int _TAG_ODB_DATA = 1;
    private static final int _TAG_MODIF_NOTIF_TO_CSE = 2;
    private static final int _TAG_EXTENSION_CONTAINER = 3;

    private ODBData odbData;
    private ModificationInstruction modifyNotificationToCSE;
    private MAPExtensionContainer extensionContainer;

    public ModificationRequestForODBdataImpl() {
        super("ModificationRequestForODBdataImpl");
    }

    public ModificationRequestForODBdataImpl(ODBData odbData,
                                             ModificationInstruction modifyNotificationToCSE,
                                             MAPExtensionContainer extensionContainer) {
        super("ModificationRequestForODBdataImpl");

        this.odbData = odbData;
        this.modifyNotificationToCSE = modifyNotificationToCSE;
        this.extensionContainer = extensionContainer;
    }

    public ODBData getOdbData() {
        return this.odbData;
    }
    
    public ModificationInstruction getModifyNotificationToCSE() {
        return this.modifyNotificationToCSE;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    protected void _decode(AsnInputStream asnIS, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.odbData = null;
        this.modifyNotificationToCSE = null;
        this.extensionContainer = null;

        AsnInputStream ais = ansIS.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _TAG_ODB_DATA:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter odbData is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.odbData = new ODBDataImpl();
                        ((ODBDataImpl)this.odbData).decodeAll(ais);
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
        if (this.odbData != null) {
            ((ODBDataImpl)this.odbData).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_ODB_DATA);
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

        if (this.odbData != null) {
            sb.append("odbData=");
            sb.append(this.odbData);
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

