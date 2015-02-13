package org.iungo.message.api;

import org.iungo.result.api.Result;

public interface ReceiveMessage {

	Result receive(Message message);
}
