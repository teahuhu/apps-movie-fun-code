package org.superbiz.moviefun;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DbConfig {
    @Bean
    HibernateJpaVendorAdapter getAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        adapter.setGenerateDdl(true);
        return adapter;
    }

    public static DataSource getDataSource(String url, String username, String password) {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(url);
        ds.setUser(username);
        ds.setPassword(password);
        return getConnectionPool(ds);
    }

    public static DataSource getConnectionPool(DataSource ds){
        HikariConfig config = new HikariConfig();
        config.setDataSource(ds);
        return new HikariDataSource(config);
    }


    public static LocalContainerEntityManagerFactoryBean getEntityManager(DataSource ds, HibernateJpaVendorAdapter adapter, String unitName){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds);
        em.setJpaVendorAdapter(adapter);
        em.setPackagesToScan("org.superbiz.moviefun");
        em.setPersistenceUnitName(unitName);
        return em;
    }

    @Configuration
    public static class Movies {
        private final Logger logger = LoggerFactory.getLogger(this.getClass());
        @Value("${moviefun.datasources.movies.url}") String url;
        @Value("${moviefun.datasources.movies.username}") String username;
        @Value("${moviefun.datasources.movies.password}") String password;

        @Bean
        public DataSource moviesDataSource() {
            logger.debug("MOVIES URL: " + url);
            logger.debug("MOVIES USER: " + username);
            logger.debug("MOVIES PASSWORD: " + password);
            return getDataSource(url, username, password);
        }

        @Bean
        @Qualifier("movies")
        LocalContainerEntityManagerFactoryBean moviesEntityManagerFactoryBean(DataSource moviesDataSource, HibernateJpaVendorAdapter jpaVendorAdapter) {
            return getEntityManager(moviesDataSource, jpaVendorAdapter, "movies");
        }

        @Bean
        PlatformTransactionManager movieTransManager(@Qualifier("movies") LocalContainerEntityManagerFactoryBean factoryBean) {
            return new JpaTransactionManager(factoryBean.getObject());
        }
    }

    @Configuration
    public static class Albums {
        private final Logger logger = LoggerFactory.getLogger(this.getClass());
        @Value("${moviefun.datasources.albums.url}") String url;
        @Value("${moviefun.datasources.albums.username}") String username;
        @Value("${moviefun.datasources.albums.password}") String password;

        @Bean
        public DataSource albumsDataSource() {
            logger.debug("ALBUMS URL: " + url);
            logger.debug("ALBUMS USER: " + username);
            logger.debug("ALBUMS PASSWORD: " + password);
            return getDataSource(url, username, password);
        }

        @Bean
        @Qualifier("albums")
        LocalContainerEntityManagerFactoryBean albumsEntityManagerFactoryBean(DataSource albumsDataSource, HibernateJpaVendorAdapter jpaVendorAdapter) {
            return getEntityManager(albumsDataSource, jpaVendorAdapter, "albums");
        }

        @Bean
        PlatformTransactionManager albumTransManager(@Qualifier("albums") LocalContainerEntityManagerFactoryBean factoryBean) {
            return new JpaTransactionManager(factoryBean.getObject());
        }
    }
}
