/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.seal.apigw.cl.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author nikos
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataStoreObject implements Serializable {

    private String id;
    private String type;
    private String data;

}
