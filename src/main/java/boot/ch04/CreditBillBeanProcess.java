package boot.ch04;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

/**
 * process
 */
public class CreditBillBeanProcess implements
		ItemProcessor<CreditBillBean, CreditBillBean> {

	@Override
	public CreditBillBean process(CreditBillBean bean) throws Exception {
		String dateStr = bean.getCreateDateStr();
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateStr);
			bean.setCreateDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("process" + bean);
		return bean;
	}

}
