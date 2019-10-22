
import javax.sql.DataSource;
import simplejdbc.DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author damie
 */
public class DAOEXT extends DAO{
    
    public DAOEXT(DataSource dataSource) {
        super(dataSource);
    }
    
}
