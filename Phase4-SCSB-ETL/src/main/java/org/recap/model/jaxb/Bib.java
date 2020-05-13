package org.recap.model.jaxb;

import org.recap.model.jaxb.marc.ContentType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by pvsubrah on 6/21/16.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Bib implements Serializable {
    @XmlElement
    private String owningInstitutionId;
    @XmlElement
    private String owningInstitutionBibId;
    /**
     * The Matching institution bib id.
     */
    @XmlElement
    protected List<MatchingInstitutionBibIdType> matchingInstitutionBibId;

    /**
     * The Content.
     */
    @XmlElement(required = true, nillable = true)
    protected ContentType content;

    /**
     * Gets owning institution bib id.
     *
     * @return the owning institution bib id
     */
    public String getOwningInstitutionBibId() {
        return owningInstitutionBibId;
    }

    /**
     * Sets owning institution bib id.
     *
     * @param owningInstitutionBibId the owning institution bib id
     */
    public void setOwningInstitutionBibId(String owningInstitutionBibId) {
        this.owningInstitutionBibId = owningInstitutionBibId;
    }

    /**
     * Gets owning institution id.
     *
     * @return the owning institution id
     */
    public String getOwningInstitutionId() {
        return owningInstitutionId;
    }

    /**
     * Sets owning institution id.
     *
     * @param owningInstitutionId the owning institution id
     */
    public void setOwningInstitutionId(String owningInstitutionId) {
        this.owningInstitutionId = owningInstitutionId;
    }

    /**
     * Gets matching institution bib id.
     *
     * @return the matching institution bib id
     */
    public List<MatchingInstitutionBibIdType> getMatchingInstitutionBibId() {
        return matchingInstitutionBibId;
    }

    /**
     * Sets matching institution bib id.
     *
     * @param matchingInstitutionBibId the matching institution bib id
     */
    public void setMatchingInstitutionBibId(List<MatchingInstitutionBibIdType> matchingInstitutionBibId) {
        this.matchingInstitutionBibId = matchingInstitutionBibId;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public ContentType getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(ContentType content) {
        this.content = content;
    }
}
