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

/*
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ExtSSInfoForCSE;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ExtForwardingInfoForCSE;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ExtCallBarringInfoForCSE;


public class ExtSSInfoForCSEImpl extends SequenceBase implements ExtSSInfoForCSE {

    private static final int _TAG_EXT_FRDW_INFO_FOR_CSE = 1;
    private static final int _TAG_EXT_CALL_BAR_INFO_FOR_CSSE = 2;

    private ExtForwardingInfoForCSE extForwardingInfoForCSE;
    private ExtCallBarringInfoForCSE extCallBarringInfoForCSE;

    public ExtSSInfoForCSEImpl() {
        super("ExtSSInfoForCSE");
    }

    public ExtSSInfoForCSEImpl(ExtForwardingInfoForCSE extForwardingInfoForCSE,
                               ExtCallBarringInfoForCSE extCallBarringInfoForCSE) {
        super("ExtSSInfoForCSE");
    
        this.extForwardingInfoForCSE = extForwardingInfoForCSE;
        this.extCallBarringInfoForCSE = extCallBarringInfoForCSE;
    }

    public ExtForwardingInfoForCSE getForwardingInfoForCSE() {
        return extForwardingInfoForCSE;
    }

    public ExtCallBarringInfoForCSE getCallBarringInfoForCSE() {
        return extCallBarringInfoForCSE;
    }

    @Override
    protected void _decode(AsnInputStream asnIS, int length) throws MAPParsingComponentException, IOException, AsnException {
    }

    public void encodeData(AsnOutputStream asnOs) throws MAPException {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.extForwardingInfoForCSE != null) {
            sb.append("extForwardingInfoForCSE=");
            sb.append(this.extForwardingInfoForCSE);
        }
        if (sb.length() > 0) sb.append(", ");
        if (this.extCallBarringInfoForCSE != null) {
            sb.append("extCallBarringInfoForCSE=");
            sb.append(this.extCallBarringInfoForCSE);
        }

        sb.append("]");
        return sb.toString();
    }

}
*/
