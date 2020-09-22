package de.te2m.project.service.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "command_log")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
		@NamedQuery(
				name = CommandLog.QUERY_BY_PROJECT_ID,
				query = "select u from CommandLog u where u.projectId = :projectID"),
		@NamedQuery(
				name = CommandLog.QUERY_BY_MESSAGE_ID,
				query = "select u from CommandLog u where u.messageId = :messageID"),
		@NamedQuery(
				name = CommandLog.QUERY_BY_USER_ID,
				query = "select u from CommandLog u where u.userId = :messageID")
})
public class CommandLog extends AbstractEntity {

	public static final String QUERY_BY_PROJECT_ID = "getCommandsByProjectID";

	public static final String QUERY_BY_MESSAGE_ID = "getCommandsByMessageID";

	public static final String QUERY_BY_USER_ID = "getCommandsByUserID";

	@Column(unique = true)
	private String messageId;

	private String userId;

	private String projectId;

	@Lob
	private String rawData;

	public String getMessageId() {
		return messageId;
	}

	public CommandLog setMessageId(String messageId) {
		this.messageId = messageId;
		return this;
	}

	public String getRawData() {
		return rawData;
	}

	public CommandLog setRawData(String rawData) {
		this.rawData = rawData;
		return this;
	}
}
