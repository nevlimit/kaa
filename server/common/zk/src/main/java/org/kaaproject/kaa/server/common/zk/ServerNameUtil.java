/*
 * Copyright 2014 CyberVision, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kaaproject.kaa.server.common.zk;

import java.nio.charset.Charset;
import java.util.zip.CRC32;

import org.kaaproject.kaa.server.common.zk.gen.ConnectionInfo;

/**
 *  
 * @author Andrey Shvayka
 *
 */
public class ServerNameUtil {
    /** Delimiter in DNS name host:port. */
    private static final String HOST_PORT_DELIMITER = ":";
    private static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     * Builds name based on connection info properties
     * @param connectionInfo
     * @return name 
     */
    public static String getNameFromConnectionInfo(ConnectionInfo connectionInfo) {
        StringBuilder name = new StringBuilder();
        name.append(connectionInfo.getThriftHost());
        name.append(HOST_PORT_DELIMITER);
        name.append(connectionInfo.getThriftPort());
        return name.toString();
    }
    
    public static int crc32(ConnectionInfo info) {
        CRC32 crc32 = new CRC32();
        crc32.update(getNameFromConnectionInfo(info).getBytes(UTF8));
        return (int) crc32.getValue();
    }
}
