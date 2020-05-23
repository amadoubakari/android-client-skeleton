package client.android.dao.db;

import com.flys.generictools.dao.daoImpl.GenericDaoImpl;
import com.j256.ormlite.dao.Dao;

public class UserDaoImpl  extends GenericDaoImpl <User,Long>{


    @Override
    public Dao<User, Long> getDao() {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public Class<User> getEntityClassManaged() {
        return User.class;
    }
}
