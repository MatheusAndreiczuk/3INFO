/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 2info2021
 */

@Entity
public class Tabela implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double tinss1, tinss2, tinss3, tinss4;
    private double ainss1, ainss2, ainss3, ainss4;
    private double tsf, vsf, dedpdep;
    private double tirrf1, tirrf2, tirrf3, tirrf4;
    private double airrf2, airrf3, airrf4, airrf5;
    private double dirrf2, dirrf3, dirrf4, dirrf5;

    /**
     * @return the tinss1
     */
    public double getTinss1() {
        return tinss1;
    }

    /**
     * @param tinss1 the tinss1 to set
     */
    public void setTinss1(double tinss1) {
        this.tinss1 = tinss1;
    }

    /**
     * @return the tinss2
     */
    public double getTinss2() {
        return tinss2;
    }

    /**
     * @param tinss2 the tinss2 to set
     */
    public void setTinss2(double tinss2) {
        this.tinss2 = tinss2;
    }

    /**
     * @return the tinss3
     */
    public double getTinss3() {
        return tinss3;
    }

    /**
     * @param tinss3 the tinss3 to set
     */
    public void setTinss3(double tinss3) {
        this.tinss3 = tinss3;
    }

    /**
     * @return the tinss4
     */
    public double getTinss4() {
        return tinss4;
    }

    /**
     * @param tinss4 the tinss4 to set
     */
    public void setTinss4(double tinss4) {
        this.tinss4 = tinss4;
    }

    /**
     * @return the ainss1
     */
    public double getAinss1() {
        return ainss1;
    }

    /**
     * @param ainss1 the ainss1 to set
     */
    public void setAinss1(double ainss1) {
        this.ainss1 = ainss1;
    }

    /**
     * @return the ainss2
     */
    public double getAinss2() {
        return ainss2;
    }

    /**
     * @param ainss2 the ainss2 to set
     */
    public void setAinss2(double ainss2) {
        this.ainss2 = ainss2;
    }

    /**
     * @return the ainss3
     */
    public double getAinss3() {
        return ainss3;
    }

    /**
     * @param ainss3 the ainss3 to set
     */
    public void setAinss3(double ainss3) {
        this.ainss3 = ainss3;
    }

    /**
     * @return the ainss4
     */
    public double getAinss4() {
        return ainss4;
    }

    /**
     * @param ainss4 the ainss4 to set
     */
    public void setAinss4(double ainss4) {
        this.ainss4 = ainss4;
    }

    /**
     * @return the vsf
     */
    public double getVsf() {
        return vsf;
    }

    /**
     * @param vsf the vsf to set
     */
    public void setVsf(double vsf) {
        this.vsf = vsf;
    }

    /**
     * @return the tirrf1
     */
    public double getTirrf1() {
        return tirrf1;
    }

    /**
     * @param tirrf1 the tirrf1 to set
     */
    public void setTirrf1(double tirrf1) {
        this.tirrf1 = tirrf1;
    }

    /**
     * @return the tirrf2
     */
    public double getTirrf2() {
        return tirrf2;
    }

    /**
     * @param tirrf2 the tirrf2 to set
     */
    public void setTirrf2(double tirrf2) {
        this.tirrf2 = tirrf2;
    }

    /**
     * @return the tirrf3
     */
    public double getTirrf3() {
        return tirrf3;
    }

    /**
     * @param tirrf3 the tirrf3 to set
     */
    public void setTirrf3(double tirrf3) {
        this.tirrf3 = tirrf3;
    }

    /**
     * @return the tirrf4
     */
    public double getTirrf4() {
        return tirrf4;
    }

    /**
     * @param tirrf4 the tirrf4 to set
     */
    public void setTirrf4(double tirrf4) {
        this.tirrf4 = tirrf4;
    }

    /**
     * @return the airrf2
     */
    public double getAirrf2() {
        return airrf2;
    }

    /**
     * @param airrf2 the airrf2 to set
     */
    public void setAirrf2(double airrf2) {
        this.airrf2 = airrf2;
    }

    /**
     * @return the airrf3
     */
    public double getAirrf3() {
        return airrf3;
    }

    /**
     * @param airrf3 the airrf3 to set
     */
    public void setAirrf3(double airrf3) {
        this.airrf3 = airrf3;
    }

    /**
     * @return the airrf4
     */
    public double getAirrf4() {
        return airrf4;
    }

    /**
     * @param airrf4 the airrf4 to set
     */
    public void setAirrf4(double airrf4) {
        this.airrf4 = airrf4;
    }

    /**
     * @return the dirrf2
     */
    public double getDirrf2() {
        return dirrf2;
    }

    /**
     * @param dirrf2 the dirrf2 to set
     */
    public void setDirrf2(double dirrf2) {
        this.dirrf2 = dirrf2;
    }

    /**
     * @return the dirrf3
     */
    public double getDirrf3() {
        return dirrf3;
    }

    /**
     * @param dirrf3 the dirrf3 to set
     */
    public void setDirrf3(double dirrf3) {
        this.dirrf3 = dirrf3;
    }

    /**
     * @return the dirrf4
     */
    public double getDirrf4() {
        return dirrf4;
    }

    /**
     * @param dirrf4 the dirrf4 to set
     */
    public void setDirrf4(double dirrf4) {
        this.dirrf4 = dirrf4;
    }

    /**
     * @return the tsf
     */
    public double getTsf() {
        return tsf;
    }

    /**
     * @param tsf the tsf to set
     */
    public void setTsf(double tsf) {
        this.tsf = tsf;
    }

    /**
     * @return the airrf5
     */
    public double getAirrf5() {
        return airrf5;
    }

    /**
     * @param airrf5 the airrf5 to set
     */
    public void setAirrf5(double airrf5) {
        this.airrf5 = airrf5;
    }

    /**
     * @return the dirrf5
     */
    public double getDirrf5() {
        return dirrf5;
    }

    /**
     * @param dirrf5 the dirrf5 to set
     */
    public void setDirrf5(double dirrf5) {
        this.dirrf5 = dirrf5;
    }

    /**
     * @return the dedpdep
     */
    public double getDedpdep() {
        return dedpdep;
    }

    /**
     * @param dedpdep the dedpdep to set
     */
    public void setDedpdep(double dedpdep) {
        this.dedpdep = dedpdep;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
