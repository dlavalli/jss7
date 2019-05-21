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
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtCallBarringFeature;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ExtCallBarringInfoForCSE;
import org.restcomm.protocols.ss7.map.api.service.supplementary.Password;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.service.supplementary.SSCodeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtCallBarringFeatureImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.PasswordImpl;

public class ExtCallBarringInfoForCSEImpl extends SequenceBase implements ExtCallBarringInfoForCSE {

    private static final int _TAG_SS_CODE = 1;
    private static final int _TAG_EXT_FORWD_FEATURE = 2;
    private static final int _TAG_PASSWORD = 3;
    private static final int _TAG_WRONG_PASSWD_ATTMPT_COUNT = 4;
    private static final int _TAG_NOTIF_TO_CSE = 5;
    private static final int _TAG_EXTENSIONCONTAINER = 6;

    private SSCode ssCode;
    private ArrayList<ExtCallBarringFeature> callBarringFeatureList;
    private Password password;
    private Integer wrongPasswdAttemptCounter;
    private boolean notificationToCse;
    private MAPExtensionContainer extensionContainer;

    public ExtCallBarringInfoForCSEImpl() {
        super("ExtCallBarringInfoForCSEImpl");
    }   

    public ExtCallBarringInfoForCSEImpl(SSCode ssCode,
                                        ArrayList<ExtCallBarringFeature> callBarringFeatureList,
                                        Password password,
                                        Integer wrongPasswdAttemptCounter,
                                        boolean notificationToCse,
                                        MAPExtensionContainer extensionContainer) {
        super("ExtCallBarringInfoForCSEImpl");

        this.ssCode = ssCode;
        this.callBarringFeatureList = callBarringFeatureList;
        this.password = password;
        this.wrongPasswdAttemptCounter = wrongPasswdAttemptCounter;
        this.notificationToCse = notificationToCse;
        this.extensionContainer = extensionContainer;
    } 
  
    public SSCode getSsCode() { // mandatory
        return this.ssCode;
    }

    public ArrayList<ExtCallBarringFeature> getCallBarringFeatureList() {   // mandatory
        return this.callBarringFeatureList;
    }

    public Password getPassword() {
        return this.password
    }

    public Integer getWrongPasswordAttemptsCounter() {
        return this.wrongPasswdAttemptCounter;
    }

    public boolean getNotificationToCSE() {
        return this.notificationToCse;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    protected void _decode(AsnInputStream asnIS, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.ssCode = null;
        this.callBarringFeatureList = null;
        this.password = null;
        this.wrongPasswdAttemptCounter = null;
        this.notificationToCse = false
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
                                    + ".callBarringFeatureList: Parameter callBarringFeatureList is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        ExtCallBarringFeature callBarringFeature;
                        AsnInputStream ais2 = ais.readSequenceStream();
                        this.callBarringFeatureList = new ArrayList<ExtForwFeature>();
                        while (true) {
                            if (ais2.available() == 0)
                                break;

                            if (ais2.readTag() != Tag.SEQUENCE || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || ais2.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".callBarringFeatureList: Parameter callBarringFeatureList is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);

                            callBarringFeature = new ExtCallBarringFeatureImpl();
                            ((ExtCallBarringFeatureImpl)callBarringFeature).decodeAll(ais2);
                            this.callBarringFeatureList.add(callBarringFeature);
                        }

                        if (this.callBarringFeatureList.size() < 1 || this.callBarringFeatureList.size() > 10) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter callBarringFeatureList size must be from 1 to 10, found: "
                                    + this.callBarringFeatureList.size(), MAPParsingComponentExceptionReason.MistypedParameter);
                        }

                        break;

                    case _TAG_PASSWORD:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".password: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.password = new PasswordImpl();
                        ((PasswordImpl) this.password).decodeAll(ais);
                        break;
    
                    case _TAG_WRONG_PASSWD_ATTMPT_COUNT:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " wrongPasswdAttemptCount: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.wrongPasswdAttemptCount = (int) ais.readInteger();
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

        if (this.callBarringFeatureList == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": callBarringFeatureList parameter is mandatory but is not found",
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
        if (this.callBarringFeatureList != null) {
            try {
                if (this.callBarringFeatureList.size() < 1 || this.callBarringFeatureList.size() > 10) {
                    throw new MAPException("Error while encoding " + _PrimitiveName
                            + " size callBarringFeatureList is out of range (1..10). Actual size: " + this.callBarringFeatureList.size());
                }

                asnOs.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG_EXT_FORWD_FEATURE);
                int pos = asnOs.StartContentDefiniteLength();
                for (ExtCallBarringFeature feature: this.callBarringFeatureList) {
                    ((ExtCallBarringFeatureImpl)feature).encodeAll(asnOs);
                }
                asnOs.FinalizeContent(pos);

            } catch (AsnException ae) {
                throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + ae.getMessage(), ae);
            }
        }
        if (this.password != null) {
            ((PasswordImpl)this.password).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_PASSWORD);
        }
        if (this.wrongPasswdAttemptCounter != null) {
            if (this.wrongPasswdAttemptCounter < 0 || this.wrongPasswdAttemptCounter > 4) {
                throw new MAPException("Error while encoding " + _PrimitiveName
                                     + " parameter wrongPasswdAttemptCounter is out of range (0..4). Actual value: " + this.wrongPasswdAttemptCounter);
            }

            asnOs.writeInteger(this.wrongPasswdAttemptCounter);
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
        if (this.callBarringFeatureList != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("callBarringFeatureList=[");
            boolean firstItem = true;
            for(ExtCallBarringFeature feature: callBarringFeatureList) {
                if (firstItem) {
                    firstItem = false;
                } else {
                    sb.append(", ");
                }
                sb.append(feature);
            }
            sb.append("]");
        }
        if (this.password != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("password=");
            sb.append(this.password);
        }
        if (this.wrongPasswdAttemptCounter != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("wrongPasswdAttemptCounter=");
            sb.append(this.wrongPasswdAttemptCounter);
        }
        if (this.notificationToCSE) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("notificationToCSE");
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
