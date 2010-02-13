
package com.google.code.linkedinapi.schema.xpp;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.google.code.linkedinapi.schema.ItemContent;
import com.google.code.linkedinapi.schema.MailboxItem;
import com.google.code.linkedinapi.schema.Recipients;

public class MailboxItemImpl
    extends BaseSchemaEntity
    implements MailboxItem
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8720504708417425242L;
	protected RecipientsImpl recipients;
    protected String subject;
    protected String body;
    protected ItemContentImpl itemContent;

    public Recipients getRecipients() {
        return recipients;
    }

    public void setRecipients(Recipients value) {
        this.recipients = ((RecipientsImpl) value);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String value) {
        this.subject = value;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String value) {
        this.body = value;
    }

    public ItemContent getItemContent() {
        return itemContent;
    }

    public void setItemContent(ItemContent value) {
        this.itemContent = ((ItemContentImpl) value);
    }

	@Override
	public void init(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, null);

        while (parser.nextTag() == XmlPullParser.START_TAG) {
        	String name = parser.getName();
        	
        	if (name.equals("recipients")) {
    			RecipientsImpl recipientsImpl = new RecipientsImpl();
    			recipientsImpl.init(parser);
    			setRecipients(recipientsImpl);
        	} else if (name.equals("item-content")) {
    			ItemContentImpl itemContentImpl = new ItemContentImpl();
    			itemContentImpl.init(parser);
    			setItemContent(itemContentImpl);
        	} else if (name.equals("subject")) {
        		setSubject(XppUtils.getElementValueFromNode(parser));
        	} else if (name.equals("body")) {
        		setBody(XppUtils.getElementValueFromNode(parser));
        	}
        }
	}

	@Override
	public void toXml(XmlSerializer serializer) throws IOException {
		XmlSerializer element = serializer.startTag(null, "mailbox-item");
		XppUtils.setElementValueToNode(element, "subject", getSubject());
		XppUtils.setElementValueToNode(element, "body", getBody());
		
		if (getRecipients() != null) {
			((RecipientsImpl) getRecipients()).toXml(serializer);
		}
		if (getItemContent() != null) {
			((ItemContentImpl) getItemContent()).toXml(serializer);
		}
		serializer.endTag(null, "mailbox-item");
	}

}
