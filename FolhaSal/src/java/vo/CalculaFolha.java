/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author 2info2021
 */
public class CalculaFolha {

    private Funcionario funcionario;
    private Tabela tabela;
    private double inss;
    private double sf;
    private double vt;
    private double salliq;
    private double irrf;

    public void calcula() {
        if (funcionario.getVt().equals("S")) {
            vt = funcionario.getSalario() * 6 / 100;
        } else {
            vt = 0;
        }
        
        
        
        if (funcionario.getSalario() <= tabela.getTsf()) {
            sf = funcionario.getDep14() * tabela.getVsf();
        } else {
            sf = 0;
        }
        
        
        
        if (funcionario.getSalario() <= tabela.getTinss1()) {
            inss = funcionario.getSalario() * tabela.getAinss1() / 100;
        } else {
            if (funcionario.getSalario() <= tabela.getTinss2()) {

                inss = tabela.getTinss1() * tabela.getAinss1() / 100 + (funcionario.getSalario() - tabela.getTinss1())
                        * tabela.getAinss2() / 100;
            } else {
                if (funcionario.getSalario() <= tabela.getTinss3()) {

                    inss = tabela.getTinss1() * tabela.getAinss1() / 100 + (tabela.getTinss2() - tabela.getTinss1())
                            * tabela.getAinss2() / 100 + (funcionario.getSalario() - tabela.getTinss2()) * tabela.getAinss3() / 100;

                } else {
                    if (funcionario.getSalario() <= tabela.getTinss4()) {
                        inss = tabela.getTinss1() * tabela.getAinss1() / 100 + (tabela.getTinss2() - tabela.getTinss1())
                                * tabela.getAinss2() / 100 + (tabela.getTinss3() - tabela.getTinss2()) * tabela.getAinss3() / 100
                                + (funcionario.getSalario() - tabela.getTinss3()) * tabela.getAinss4() / 100;

                    } else {

                        inss = tabela.getTinss1() * tabela.getAinss1() / 100 + (tabela.getTinss2() - tabela.getTinss1())
                                * tabela.getAinss2() / 100 + (tabela.getTinss3() - tabela.getTinss2()) * tabela.getAinss3() / 100
                                + (tabela.getTinss4() - tabela.getTinss3()) * tabela.getAinss4() / 100;

                    }
                }
            }
        }
        
        
        
        double bc = funcionario.getSalario() - inss - (funcionario.getDepir() * tabela.getDedpdep());
        if (bc <= tabela.getTirrf1()) {
            irrf = 0;
        } else {
            if (bc <= tabela.getTirrf2()) {
                irrf = bc * (tabela.getAirrf2() / 100) - tabela.getDirrf2();
            } else {
                if (bc <= tabela.getTirrf3()) {
                    irrf = bc * (tabela.getAirrf3() / 100) - tabela.getDirrf3();
                } else {
                    if (bc <= tabela.getTirrf4()) {
                        irrf = bc * (tabela.getAirrf4() / 100) - tabela.getDirrf4();
                    } else {
                        irrf = bc * (tabela.getAirrf5() / 100) - tabela.getDirrf5();
                    }
                }
            }
        }
        
        
        
        salliq = funcionario.getSalario() - inss + sf - vt - irrf;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the tabela
     */
    public Tabela getTabela() {
        return tabela;
    }

    /**
     * @param tabela the tabela to set
     */
    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    /**
     * @return the inss
     */
    public double getInss() {
        return inss;
    }

    /**
     * @param inss the inss to set
     */
    public void setInss(double inss) {
        this.inss = inss;
    }

    /**
     * @return the sf
     */
    public double getSf() {
        return sf;
    }

    /**
     * @param sf the sf to set
     */
    public void setSf(double sf) {
        this.sf = sf;
    }

    /**
     * @return the vt
     */
    public double getVt() {
        return vt;
    }

    /**
     * @param vt the vt to set
     */
    public void setVt(double vt) {
        this.vt = vt;
    }

    /**
     * @return the salliq
     */
    public double getSalliq() {
        return salliq;
    }

    /**
     * @param salliq the salliq to set
     */
    public void setSalliq(double salliq) {
        this.salliq = salliq;
    }

    /**
     * @return the irrf
     */
    public double getIrrf() {
        return irrf;
    }

    /**
     * @param irrf the irrf to set
     */
    public void setIrrf(double irrf) {
        this.irrf = irrf;
    }
}
