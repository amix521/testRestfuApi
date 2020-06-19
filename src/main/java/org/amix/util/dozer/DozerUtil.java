package org.amix.util.dozer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;


/**
 * 
 * @className: DozerUtils
 * @description: DTO/VO/DO等之间的转换
 * 
 *
 */

public class DozerUtil {
    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
	
	@Value("${dozer.dozerPath}")
	private static String dozerPath;
	
    private static DozerBeanMapper dozer;

    static {
        if (dozer == null) {
            dozer = new DozerBeanMapper();
            
            File file = null;
    		try {
    			file = ResourceUtils.getFile("classpath:"+dozerPath);
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            String[] dozerMaps=file.list();
            
            List<String> mappingFileUrls = Arrays.asList(dozerMaps);
//             List<String> mappingFileUrls = Arrays.asList("dozer/dozer-mapping.xml");
             dozer.setMappingFiles(mappingFileUrls);
        }
    }
    
    /**
     * 
     * @title: map
     * @description: 单个对象相互转换
     *
     * @param source 源对象
     * @param destinationClass 目标对象
     * @return
     * @date 2017年11月8日 下午6:08:54
     */
    public static <T> T map ( Object source, Class<T> destinationClass ) {

        return dozer.map(source, destinationClass);
    }

    /**
     * 
     * @title: mapList
     * @description: 集合对象相互转换
     *
     * @param sourceList
     * @param destinationClass
     * @return
     * @date 2017年11月8日 下午6:09:41
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> mapList ( Collection sourceList, Class<T> destinationClass ) {
        List<T> destinationList = new ArrayList<T>();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }

        return destinationList;
    }


}
