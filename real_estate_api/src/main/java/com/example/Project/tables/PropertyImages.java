package com.example.Project.tables;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
public class PropertyImages {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String propertyImageId;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] propertyImage1;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] propertyImage2;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] propertyImage3;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] propertyImage4;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] mapimage;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] topmapimage;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] groundmapimage;
    @ManyToOne
    @JoinColumn(name = "propertyId",referencedColumnName = "propertyId")
    private Property propertyData;
}
