package org.iungo.message.api;

import org.iungo.result.api.Result;

public interface SendMessage {

	Result send(Message message);
}
