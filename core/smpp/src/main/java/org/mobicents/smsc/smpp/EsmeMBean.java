/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2015, Telestax Inc and individual contributors
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

package org.mobicents.smsc.smpp;

import com.cloudhopper.smpp.jmx.DefaultSmppSessionMXBean;
//import org.restcomm.smpp.jmx.DefaultSmppSessionMXBean;


/**
 * 
 * @author Amit Bhayani
 * 
 */
public interface EsmeMBean extends DefaultSmppSessionMXBean, SslConfigurationWrapperMBean {
	boolean isStarted();

	String getClusterName();

	/**
	 * Defines ESME TON. if SMPP Session Type is CLIENT this TON will be used in
	 * BIND request, if SMPP Session Type is SERVER, incoming BIND request
	 * should have same TON as configured here. If configured is null(-1), SMSC
	 * will ignore in both the cases
	 * 
	 * @return
	 */
	int getEsmeTon();

	void setEsmeTon(int esmeTon);

	/**
	 * Defines ESME NPI. if SMPP Session Type is CLIENT this NPI will be used in
	 * BIND request, if SMPP Session Type is SERVER, incoming BIND request
	 * should have same NPI as configured here. If configured is null(-1), SMSC
	 * will ignore in both the cases
	 * 
	 * @return
	 */
	int getEsmeNpi();

	void setEsmeNpi(int esmeNpi);

	/**
	 * Defines ESME Address Range. if SMPP Session Type is CLIENT this address
	 * range will be used in BIND request, if SMPP Session Type is SERVER,
	 * incoming BIND request should have same address range as configured here.
	 * If configured is null, SMSC will ignore in both the cases
	 * 
	 * @return
	 */
	String getEsmeAddressRange();

	void setEsmeAddressRange(String sourceAddressRange);

	String getHost();

    int getPort();

	/**
	 * every SMS coming into SMSC via this ESME should have same source_addr_ton
	 * as configured here. If the value here is null(-1) or it's not null and
	 * match's, SMSC will compare source_addr_npi and source_addr as mentioned
	 * below. If it doesn't match SMSC will reject this SMS with error code
	 * "0x0000000A" - Invalid Source Address.
	 * 
	 * @return
	 */
	int getSourceTon();

	void setSourceTon(int sourceTon);

	/**
	 * every SMS coming into SMSC via this ESME should have same source_addr_npi
	 * as configured here. If the value here is null(-1)or it's not null and
	 * match's, SMSC will compare source_addr as mentioned below. If it doesn't
	 * match SMSC will reject this SMS with error code "0x0000000A" - Invalid
	 * Source Address.
	 * 
	 * @return
	 */
	int getSourceNpi();

	void setSourceNpi(int sourceNpi);

	/**
	 * every SMS coming into SMSC via this ESME should have same source_addr as
	 * mentioned here. This is regular java expression. Default value is
	 * ^[0-9a-zA-Z]* If it match's, SMSC will accept incoming SMS and process
	 * further. If it doesn't match SMSC will reject this SMS with error code
	 * "0x0000000A" - Invalid Source Address.
	 * 
	 * @return
	 */
	String getSourceAddressRange();

	void setSourceAddressRange(String sourceAddressRange);

	/**
	 * The {@link DefaultSmsRoutingRule} will try to match the dest_addr_ton of
	 * outgoing SMS with one configured here. If configured value is null(-1) or
	 * it's not null and match's, SMSC will compare dest_addr_npi and
	 * destination_addr as below. It it doesn't match, SMSC will select next
	 * ESME in list for matching routing rule
	 * 
	 * @return
	 */
	int getRoutingTon();

	void setRoutingTon(int routingTon);

	/**
	 * The {@link DefaultSmsRoutingRule} will try to match the dest_addr_npi
	 * with one configured here. If configured value is null(-1)or it's not null
	 * and match's, SMSC will compare destination_addr as below. It it doesn't
	 * match, SMSC will select next ESME in list for matching routing rule
	 * 
	 * @return
	 */
	int getRoutingNpi();

	void setRoutingNpi(int sourceNpi);

	/**
	 * The {@link DefaultSmsRoutingRule} will try to match destination_addr
	 * here. This is regular java expression. Default value is ^[0-9a-zA-Z]*. If
	 * it match's, SMSC will send the SMS out over this SMPP connection. If it
	 * doesn't match, SMSC will select next ESME in list for matching routing
	 * rule
	 * 
	 * @return
	 */
	String getRoutingAddressRange();

	void setRoutingAddressRange(String sourceAddressRange);

	/**
	 * Sets the default window size. Value takes effect only when ESME is
	 * restarted.
	 * 
	 * The window size is the amount of unacknowledged requests that are
	 * permitted to be outstanding/unacknowledged at any given time. If more
	 * requests are added, the underlying stack will throw an exception.
	 * 
	 * This value is set only when ESME is defined as Client side. For Server
	 * side this value is taken from the 'SMPP Server Settings'.
	 * 
	 * @param windowSize
	 */
	void setWindowSize(int windowSize);

	/**
	 * Value takes effect only when ESME is restarted.
	 * 
	 * Default value is 10000 milli seconds. This parameter is used to specify
	 * the time within which the connection to a remote SMSC server should be
	 * established.
	 * 
	 * This is useful only when ESME is defined as Client Side. For Server side
	 * this value is taken from the the 'SMPP Server Settings'.
	 * 
	 * @param connectTimeout
	 */
	void setConnectTimeout(long connectTimeout);

	long getConnectTimeout();

	/**
	 * Value takes effect only when ESME is restarted.
	 * 
	 * Default value is -1 (disabled). This parameter is used to specify the
	 * time to wait in milli seconds for an endpoint to respond to before it
	 * expires. This is useful only when ESME is defined as Client Side. For
	 * Server side this value is taken from the the 'SMPP Server Settings'.
	 * 
	 * @param requestExpiryTimeout
	 */
	void setRequestExpiryTimeout(long requestExpiryTimeout);

	/**
	 * Value takes effect only when ESME is restarted.
	 * 
	 * Default value is -1 (disabled). This parameter is used to specify the
	 * time between executions of monitoring the window for requests that
	 * expire. It is recommended that this value, generally, either matches or
	 * is half the value of 'request-expiry-timeout'. Therefore, in the worst
	 * case scenario, a request could take upto 1.5 times the
	 * 'requestExpiryTimeout' to clear out.
	 * 
	 * This is useful only when ESME is defined as Client Side. For Server side
	 * this value is taken from the the 'SMPP Server Settings'.
	 * 
	 * @param windowMonitorInterval
	 */
	void setWindowMonitorInterval(long windowMonitorInterval);

	/**
	 * Value takes effect only when ESME is restarted.
	 * 
	 * Default value is 60000 milli seconds. This parameter is used to specify
	 * the time to wait until a slot opens up in the 'sendWindow'.
	 * 
	 * This is useful only when ESME is defined as Client Side. For Server side
	 * this value is taken from the the 'SMPP Server Settings'.
	 * 
	 * @param windowWaitTimeout
	 */
	void setWindowWaitTimeout(long windowWaitTimeout);

	/**
	 * Default value is 30000 milli seconds. When SMSC connects to a remote
	 * server as CLIENT, it sends an 'ENQUIRE_LINK' after every configured
	 * enquire-link-delay.
	 * 
	 * @param enquireLinkDelay
	 */
	void setEnquireLinkDelay(int enquireLinkDelay);

    int getEnquireLinkDelay();

    void setPassword(String password);

}
