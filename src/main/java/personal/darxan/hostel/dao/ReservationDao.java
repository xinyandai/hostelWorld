package personal.darxan.hostel.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import personal.darxan.hostel.dao.base.BaseDao;
import personal.darxan.hostel.dao.base.SQLAppend;
import personal.darxan.hostel.model.Reservation;
import personal.darxan.hostel.tool.MyPair;
import personal.darxan.hostel.vo.PaginationResult;
import personal.darxan.hostel.vo.ReservationRestrict;

import java.util.List;

/**
 * Created by darxan on 2017/2/19.
 */
@Repository
public class ReservationDao extends BaseDao<Reservation, Long> {


    private PaginationResult getReservation(
            ReservationRestrict reservationRestrict, MyPair ... myPairs) {

        SQLAppend sqlAppend = new SQLAppend(getSession(), "Reservation", "hostel", "member");
        sqlAppend.addLike("people", reservationRestrict.getKeyword());
        for (MyPair pair:myPairs) {
            sqlAppend.addEq(pair.getFirst(), pair.getSecond());
        }
        if (reservationRestrict.getDateLower()!=null&&reservationRestrict.getDateUpper()!=null) {
            sqlAppend.addGE("endDate", "?");
            sqlAppend.addLE("startDate", "?");
        }
        Query query = sqlAppend.fetchCount();
        if (reservationRestrict.getDateLower()!=null&&reservationRestrict.getDateUpper()!=null) {
            query.setDate(0, reservationRestrict.getDateLower());
            query.setDate(1, reservationRestrict.getDateUpper());
        }
        PaginationResult paginationResult = new PaginationResult();
        paginationResult.setTotalPages(Integer.parseInt(query.uniqueResult().toString()));



        query = sqlAppend.fetchList();
        if (reservationRestrict.getDateLower()!=null&&reservationRestrict.getDateUpper()!=null) {
            query.setDate(0, reservationRestrict.getDateLower());
            query.setDate(1, reservationRestrict.getDateUpper());
        }
        query.setFirstResult(reservationRestrict.getPageSize()*(reservationRestrict.getPage()-1));
        query.setMaxResults(reservationRestrict.getPageSize());
        List list = query.list();
        paginationResult.setItems(list);

        return paginationResult;
    }

    public PaginationResult getReservationByUser(Object userId, ReservationRestrict reservationRestrict) {

        return getReservation(reservationRestrict,
                new MyPair<String,Object>("_joinedTable_member.memberId", userId));
    }

    public PaginationResult getReservationByHostel(Object hostelId,ReservationRestrict reservationRestrict) {

        return getReservation(reservationRestrict,
                new MyPair<String,Object>("_joinedTable_hostel.hostelId", hostelId));
    }

    public PaginationResult getCheckInByUser(Object userId, ReservationRestrict reservationRestrict) {
        return getReservation(
                reservationRestrict,
                new MyPair<String,Object>("_joinedTable_member.memberId", userId),
                new MyPair<String,Object>("checkIn", 1)
        );
    }

    public PaginationResult getCheckInByHostel(Object hostelId, ReservationRestrict reservationRestrict) {
        return getReservation(reservationRestrict,
                new MyPair<String,Object>("_joinedTable_hostel.hostelId", hostelId),
                new MyPair<String,Object>("checkIn", 1)
        );
    }

    public PaginationResult getCheckOutByUser(Object userId, ReservationRestrict reservationRestrict) {
        return getReservation(
                reservationRestrict,
                new MyPair<String,Object>("_joinedTable_member.memberId", userId),
                new MyPair<String,Object>("checkOut", 1)
        );
    }

    public PaginationResult getCheckOutByHostel(Object hostelId, ReservationRestrict reservationRestrict) {
        return getReservation(reservationRestrict,
                new MyPair<String,Object>("_joinedTable_hostel.hostelId", hostelId),
                new MyPair<String,Object>("checkOut", 1)
        );
//        getSession().createQuery("from Reservation r join r.member member join r.hostel hostel where r.checkOut")
//        return null;
    }

    public PaginationResult getPaymentByUser(Object userId, ReservationRestrict reservationRestrict) {
        return getReservation(
                reservationRestrict,
                new MyPair<String,Object>("_joinedTable_member.memberId", userId),
                new MyPair<String,Object>("payment", 1)
        );
    }

    public PaginationResult getPaymentByHostel(Object hostelId, ReservationRestrict reservationRestrict) {
        return getReservation(reservationRestrict,
                new MyPair<String,Object>("_joinedTable_hostel.hostelId", hostelId),
                new MyPair<String,Object>("payment", 1)
        );
//        getSession().createQuery("from Reservation r join r.member member join r.hostel hostel where r.checkOut")
//        return null;
    }
}
