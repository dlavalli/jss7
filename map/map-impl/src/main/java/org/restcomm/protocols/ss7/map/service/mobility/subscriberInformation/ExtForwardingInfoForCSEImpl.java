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

import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ExtForwardingInfoForCSE;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtForwFeature;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtForwFeatureImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSCodeImpl;

public class ExtForwardingInfoForCSEImpl extends SequenceBase implements ExtForwardingInfoForCSE {

    private static final int _TAG_SS_CODE = 1;
    private static final int _TAG_EXT_FORWD_FEATURE = 2;
    private static final int _TAG_NOTIF_TO_CSE = 3;
    private static final int _TAG_EXTENSIONCONTAINER = 4;

    private SSCode ssCode;
    private ArrayList<ExtForwFeature> extForwFeature;
    private boolean notificationToCse;
    private MAPExtensionContainer extensionContainer;

    public ExtForwardingInfoForCSEImpl() {
        super("ExtForwardingInfoForCSE");
    }

    public ExtForwardingInfoForCSEImpl(SSCode ssCode, ArrayList<ExtForwFeature> extForwFeature, boolean notificationToCse,
                                       MAPExtensionContainer extensionContainer) {
        super("ExtForwardingInfoForCSE");
    
        this.ssCode = ssCode;
        this.extForwFeature = extForwFeature;
        this.notificationToCse = notificationToCse;
        this.extensionContainer = extensionContainer;
    }


    public SSCode getSsCode() {
        return ssCode;
    }

    public ArrayList<ExtForwFeature> getForwardingFeatureList() {
        return extForwFeature;
    }

    public boolean getNotificationToCSE() {
        return notificationToCse;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    @Override
    protected void _decode(AsnInputStream asnIS, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.ssCode = null;
        this.extForwFeature = null;
        this.notificationToCse = false;
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

                    case _TAG_EXT_FORWD_FEATURE:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".extForwFeature: Parameter extForwFeature is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        ExtForwFeature forwFeature;
                        AsnInputStream ais2 = ais.readSequenceStream();
                        this.extForwFeature = new ArrayList<ExtForwFeature>();
                        while (true) {
                            if (ais2.available() == 0)
                                break;

                            if (ais2.readTag() != Tag.SEQUENCE || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || ais2.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".extForwFeature: Parameter extForwFeature is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);

                            forwFeature = new ExtForwFeatureImpl();
                            ((ExtForwFeatureImpl)forwFeature).decodeAll(ais2);
                            this.extForwFeature.add(forwFeature);
                        }

                        if (this.extForwFeature.size() < 1 || this.extForwFeature.size() > 10) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter extForwFeature size must be from 1 to 10, found: "
                                    + this.extForwFeature.size(), MAPParsingComponentExceptionReason.MistypedParameter);
                        }
                        break;

                    case _TAG_NOTIF_TO_CSE:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter notificationToCse is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        ais.readNull();
                        this.notificationToCse = Boolean.TRUE;
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

        if (this.ssCode == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": ssCode parameter is mandatory but is not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (this.extForwFeature == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": extForwFeature parameter is mandatory but is not found",
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
            throw new MAPException("AsnException when encoding " + _PrimitiveName + " : " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOs) throws MAPException {
        if (this.ssCode != null) {
            ((SSCodeImpl)this.ssCode).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_SS_CODE);
        }
        if (this.extForwFeature != null) {
            try {
                if (this.extForwFeature.size() < 1 || this.extForwFeature.size() > 10) {
                    throw new MAPException("Error while encoding " + _PrimitiveName
                            + " size extForwFeature is out of range (1..10). Actual size: " + this.extForwFeature.size());
                }

                asnOs.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG_EXT_FORWD_FEATURE);
                int pos = asnOs.StartContentDefiniteLength();
                for (ExtForwFeature feature: this.extForwFeature) {
                    ((ExtForwFeatureImpl)feature).encodeAll(asnOs);
                }
                asnOs.FinalizeContent(pos);

            } catch (AsnException ae) {
                throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + ae.getMessage(), ae);
            }
        }
        if (this.notificationToCse) {
            try {
                asnOs.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_NOTIF_TO_CSE);
            } catch (IOException e) {
                throw new MAPException("IOException when encoding parameter notificationToCse: ", e);
            } catch (AsnException e) {
                throw new MAPException("AsnException when encoding parameter notificationToCse: ", e);
            }
        }
        if (this.extensionContainer != null) {
            ((MAPExtensionContainerImpl)this.extensionContainer).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_EXTENSIONCONTAINER);
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
        if (extForwFeature != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("extForwFeature=[");
            boolean firstItem = true;
            for(ExtForwFeature feature: extForwFeature) {
                if (firstItem) {
                    firstItem = false;
                } else {
                    sb.append(", ");
                }
                sb.append(feature);
            }
            sb.append("]");
        }    
        if (this.notificationToCse) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("notificationToCse");
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

