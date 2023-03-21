//package com.hydra.spider.init;
//
//import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.ResourceLoaderAware;
//import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.core.type.filter.AnnotationTypeFilter;
//import org.springframework.stereotype.Component;
//import org.zxp.esclientrhl.annotation.ESMetaData;
//import org.zxp.esclientrhl.index.ElasticsearchIndexImpl;
//
//import java.util.Set;
//
///**
// * @author : Hydra
// * @date: 2023/2/6 10:40
// * @version: 1.0
// */
////@Component
//public class IndexInitListener implements ApplicationListener<ApplicationReadyEvent>,
//        ResourceLoaderAware {
//
//    /**
//     * 资源加载器
//     */
//    private ResourceLoader resourceLoader;
//
//    @Autowired
//    private ElasticsearchIndexImpl elasticsearchIndex;
//
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        ClassPathScanningCandidateComponentProvider scanner
//                = new ClassPathScanningCandidateComponentProvider(false) {
//            @Override
//            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
//                boolean isCandidate = false;
//                if (beanDefinition.getMetadata().isIndependent()) {
//                    if (!beanDefinition.getMetadata().isAnnotation()) {
//                        isCandidate = true;
//                    }
//                }
//                return isCandidate;
//            }
//        };
//
//        scanner.setResourceLoader(resourceLoader);
//        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(ESMetaData.class);
//        scanner.addIncludeFilter(annotationTypeFilter);
//
//        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents("com.hydra.spider");
//        for (BeanDefinition candidateComponent : candidateComponents) {
//            String beanClassName = candidateComponent.getBeanClassName();
//            try {
//                Class clazz = Class.forName(beanClassName);
//                boolean exists = elasticsearchIndex.exists(clazz);
//                if (!exists){
//                    elasticsearchIndex.createIndex(clazz);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void setResourceLoader(ResourceLoader resourceLoader) {
//        this.resourceLoader=resourceLoader;
//    }
//
//}
