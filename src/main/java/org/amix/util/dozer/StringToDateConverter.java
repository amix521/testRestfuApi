package org.amix.util.dozer;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dozer.DozerConverter;

/**
 * dozer关于时间转换处理类，好处
 * 1、可以防止默认转换时信息错误导致出错
 * 2、可以自定义转换类型格式等
 * 3、此类还可延伸出其他转换类，方便do，dto，vo等数据的转换
 * @author Administrator
 *
 */
public class StringToDateConverter extends DozerConverter<String, Date> {

    public StringToDateConverter() {
        super(String.class, Date.class);
    }

    @Override
    public String convertFrom(Date source, String destination) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        destination = formatter.format(source);
        return destination;
    }

    @Override
    public Date convertTo(String source, Date destination) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        destination = formatter.parse(source, pos);
        return destination;
    }
    
    public static void main(String[] args) {
		
	}
}