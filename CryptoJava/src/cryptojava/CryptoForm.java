/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptojava;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.*;
/**
 *
 * @author rabit
 */
public class CryptoForm extends javax.swing.JFrame {


    public CryptoForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelMessage = new javax.swing.JLabel();
        jTextFieldMessage = new javax.swing.JTextField();
        jLabelEncrypt = new javax.swing.JLabel();
        jTextFieldEncrypt = new javax.swing.JTextField();
        jButtonEncrypt = new javax.swing.JButton();
        jButtonDecrypt = new javax.swing.JButton();
        jLabelKey = new javax.swing.JLabel();
        jTextFieldKey = new javax.swing.JTextField();
        jCheckBoxRandomKey = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Шифрование AES-256");

        jLabelMessage.setText("Message");

        jLabelEncrypt.setText("Encrypted Message");

        jButtonEncrypt.setText("Encrypt");
        jButtonEncrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEncryptActionPerformed(evt);
            }
        });

        jButtonDecrypt.setText("Decrypt");
        jButtonDecrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDecryptActionPerformed(evt);
            }
        });

        jLabelKey.setText("Key");

        jCheckBoxRandomKey.setLabel("Random Key");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldMessage)
                    .addComponent(jTextFieldEncrypt)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonEncrypt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                        .addComponent(jButtonDecrypt))
                    .addComponent(jTextFieldKey)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxRandomKey)
                            .addComponent(jLabelEncrypt)
                            .addComponent(jLabelMessage)
                            .addComponent(jLabelKey))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabelMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelKey)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelEncrypt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEncrypt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxRandomKey)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEncrypt)
                    .addComponent(jButtonDecrypt))
                .addContainerGap())
        );

        jLabelKey.getAccessibleContext().setAccessibleName("jLabelKey");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEncryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEncryptActionPerformed
        try {
            String message = this.jTextFieldMessage.getText();
            byte[] messBytes = message.getBytes();
            SecretKeySpec key;
            if(this.jCheckBoxRandomKey.isSelected() == true){
                KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                keyGen.init(128);
                SecretKey keyAuto = keyGen.generateKey();
                this.jTextFieldKey.setText(byteArrayToHex(keyAuto.getEncoded()));
                key = new SecretKeySpec(keyAuto.getEncoded(), "AES");
            } else{
                byte[] keyByte = HexStringToByteArray(this.jTextFieldKey.getText());
                key = new SecretKeySpec(keyByte, "AES");
            }
            Cipher ciph = Cipher.getInstance("AES");
            ciph.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = ciph.doFinal(messBytes);
            this.jTextFieldEncrypt.setText(byteArrayToHex(encryptedBytes));
            
        } catch (Exception ex){
            this.jTextFieldEncrypt.setText(ex.getMessage());
        }
        
    }//GEN-LAST:event_jButtonEncryptActionPerformed

    private void jButtonDecryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDecryptActionPerformed
        try {
            byte[] encrypted = HexStringToByteArray(this.jTextFieldEncrypt.getText());
            byte[] keyByte = HexStringToByteArray(this.jTextFieldKey.getText());
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
            Cipher ciph = Cipher.getInstance("AES");
            ciph.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] message = ciph.doFinal(encrypted);
            this.jTextFieldMessage.setText(byteArrayToStringUTF8(message));
            
        } catch (Exception ex){
            this.jTextFieldMessage.setText(ex.getMessage());
        }
        
        
    }//GEN-LAST:event_jButtonDecryptActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CryptoForm().setVisible(true);
            }
        });
    }
    
    private String byteArrayToStringUTF8(byte[] bytes){
        String result = "";
        for(int i = 0; i < bytes.length; i++)
        {
            result += (char)bytes[i];
        }
        return result;  
    }
    
    private String byteArrayToHex(byte[] bytes){
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for(byte b: bytes)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
    
    private byte[] HexStringToByteArray(String hex){
        byte[] result = new byte[hex.length()/2];
        for(int i = 0; i < result.length; i++){
            int index = i * 2;
            int j = Integer.parseInt(hex.substring(index, index + 2), 16);
            result[i] = (byte) j;
        }
        return result;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDecrypt;
    private javax.swing.JButton jButtonEncrypt;
    private javax.swing.JCheckBox jCheckBoxRandomKey;
    private javax.swing.JLabel jLabelEncrypt;
    private javax.swing.JLabel jLabelKey;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JTextField jTextFieldEncrypt;
    private javax.swing.JTextField jTextFieldKey;
    private javax.swing.JTextField jTextFieldMessage;
    // End of variables declaration//GEN-END:variables
}
