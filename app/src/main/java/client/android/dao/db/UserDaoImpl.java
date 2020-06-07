package client.android.dao.db;

import com.flys.generictools.dao.daoImpl.GenericDaoImpl;
import com.flys.generictools.dao.db.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.android.R;
import client.android.architecture.custom.ApplicationContext;

@EBean(scope = EBean.Scope.Singleton)
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    DatabaseHelper<User, Long> databaseHelper;

    @Override
    public Dao<User, Long> getDao() {
        List<Class<?>> entityClasses = new ArrayList<>();
        entityClasses.add(User.class);
        databaseHelper = new DatabaseHelper(ApplicationContext.getContext(), R.raw.ormlite_config, entityClasses, "Skeleton.db",1 );
        try {
            return (Dao<User, Long>) databaseHelper.getDao(getEntityClassManaged());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void flush() {
        if(databaseHelper.isOpen()){
            databaseHelper.close();
        }
    }

    @Override
    public Class<User> getEntityClassManaged() {
        return User.class;
    }
}
