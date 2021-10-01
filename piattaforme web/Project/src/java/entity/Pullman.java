/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loren
 */
@Entity
@Table(name = "pullman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pullman.findAll", query = "SELECT v FROM Pullman v")
    , @NamedQuery(name = "Pullman.findByNumeroPullman", query = "SELECT v FROM Pullman v WHERE v.numeroPullman = :numeroPullman")
    , @NamedQuery(name = "Pullman.findByArrivo", query = "SELECT v FROM Pullman v WHERE v.arrivo = :arrivo")
    , @NamedQuery(name = "Pullman.findByCosto", query = "SELECT v FROM Pullman v WHERE v.costo = :costo")
    , @NamedQuery(name = "Pullman.findByDataPullman", query = "SELECT v FROM Pullman v WHERE v.dataPullman = :dataPullman")
    , @NamedQuery(name = "Pullman.findByOrarioArrivo", query = "SELECT v FROM Pullman v WHERE v.orarioArrivo = :orarioArrivo")
    , @NamedQuery(name = "Pullman.findByOrarioPartenza", query = "SELECT v FROM Pullman v WHERE v.orarioPartenza = :orarioPartenza")
    , @NamedQuery(name = "Pullman.findByPartenza", query = "SELECT v FROM Pullman v WHERE v.partenza = :partenza")})
public class Pullman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numeroPullman")
    private Integer numeroPullman;
    @Size(max = 255)
    @Column(name = "arrivo")
    private String arrivo;
    @Column(name = "costo")
    private Integer costo;
    @Column(name = "dataPullman")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPullman;
    @Size(max = 255)
    @Column(name = "orarioArrivo")
    private String orarioArrivo;
    @Size(max = 255)
    @Column(name = "orarioPartenza")
    private String orarioPartenza;
    @Size(max = 255)
    @Column(name = "partenza")
    private String partenza;
    @Column(name = "giorno")
    private int giorno;
    @Column(name = "mese")
    private int mese;
    @Column(name = "anno")
    private int anno;
    @Column(name = "postiDisponibili")
    private int postiDisponibili;

    @Version
    @Column(name = "version")
    private long versione;

    public int getPostiDisponibili() {
        return postiDisponibili;
    }

    public void setPostiDisponibili(int postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }

    public int getGiorno() {
        return giorno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public int getMese() {
        return mese;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public Pullman() {
    }

    @ManyToMany
    List<Cliente> clienti;

    public List<Cliente> getClienti() {
        return clienti;
    }

    public void setClienti(List<Cliente> clienti) {
        this.clienti = clienti;
    }

    public Pullman(Integer numeroPullman) {
        this.numeroPullman = numeroPullman;
    }

    public Integer getNumeroPullman() {
        return numeroPullman;
    }

    public void setNumeroPullman(Integer numeroPullman) {
        this.numeroPullman = numeroPullman;
    }

    public String getArrivo() {
        return arrivo;
    }

    public void setArrivo(String arrivo) {
        this.arrivo = arrivo;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Date getDataPullman() {
        return dataPullman;
    }

    public void setDataPullman(Date dataPullman) {
        this.dataPullman = dataPullman;
    }

    public String getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(String orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    public String getOrarioPartenza() {
        return orarioPartenza;
    }

    public void setOrarioPartenza(String orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroPullman != null ? numeroPullman.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pullman)) {
            return false;
        }
        Pullman other = (Pullman) object;
        if ((this.numeroPullman == null && other.numeroPullman != null) || (this.numeroPullman != null && !this.numeroPullman.equals(other.numeroPullman))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pullman[ numeroPullman=" + numeroPullman + " ]";
    }
}
