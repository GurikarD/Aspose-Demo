/*
 *    Copyright &copy; Flagstar Bank 2024.
 *
 *    Copyright in the application source code, and in the information and
 *    material therein and in their arrangement, is owned by Flagstar Bank, FSB
 *    unless otherwise indicated.
 *
 *    You shall not remove or obscure any copyright, trademark or other notices.
 *    You may not copy, republish, redistribute, transmit, participate in the
 *    transmission of, create derivatives of, alter edit or exploit in any
 *    manner any material including by storage on retrieval systems, without the
 *    express written permission of Flagstar Bank.
 */

package com.example.aspose.demo.constants;

public enum FileType
{
    HTML(".html"),
    PDF(".pdf");
    
    private final String fileType;
    
    FileType(String fileType) {
        this.fileType= fileType;
    }
    
    public String toString() {
        return this.fileType;
     }
}
