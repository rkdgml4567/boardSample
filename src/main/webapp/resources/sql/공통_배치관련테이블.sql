/*------------------------
* ����_M_�ü�_��ġ
*-------------------------
*/
DROP TABLE TB_CM_M_PR_BAT;
 CREATE TABLE TB_CM_M_PR_BAT 
(
    BAT_YM          VARCHAR(6  ) NOT NULL,
    WK_GBN					VARCHAR(2 ) NOT NULL,
    AREA_CODE       VARCHAR(5 )  NOT NULL,
    CONTRACT_YM    VARCHAR(6  ) NOT NULL,
    RESULT_CD       VARCHAR(2  ) ,
    RESULT_MSG      VARCHAR(50 ) ,
    NUM_OF_ROWS     VARCHAR(4  ) ,
    PAGE_NO         NUMBER(4),
    WK_CNT         NUMBER(4),
    TOT_CNT         NUMBER(4),
    MOD_USER	    VARCHAR(10) NOT NULL ,
    MOD_DATE	    DATE DEFAULT     SYSDATE ,
    CONSTRAINT  TB_CM_M_PR_BAT PRIMARY KEY (BAT_YM,WK_GBN,AREA_CODE, CONTRACT_YM) 
);
COMMENT ON TABLE TB_CM_M_PR_BAT      IS '����_M_�ü�_��ġ';
COMMENT ON COLUMN TB_CM_M_PR_BAT.BAT_YM         IS '��ȸ���'					 ;  
COMMENT ON COLUMN TB_CM_M_PR_BAT.WK_GBN         IS '�۾�����(11:����Ʈ�Ÿ�,12:����Ʈ������,21:���ǽ��ڸŸ�,22:���ǽ���������)'     ;  
COMMENT ON COLUMN TB_CM_M_PR_BAT.AREA_CODE      IS '�����ڵ�(�������ñ���)'          ;  
COMMENT ON COLUMN TB_CM_M_PR_BAT.CONTRACT_YM    IS '�����'              ;   
COMMENT ON COLUMN TB_CM_M_PR_BAT.RESULT_CD      IS '����ڵ�'           ;   
COMMENT ON COLUMN TB_CM_M_PR_BAT.RESULT_MSG     IS '����޼���' ;   
COMMENT ON COLUMN TB_CM_M_PR_BAT.NUM_OF_ROWS    IS '��ȸ������' ;   
COMMENT ON COLUMN TB_CM_M_PR_BAT.PAGE_NO        IS '������������ȣ'    ;   
COMMENT ON COLUMN TB_CM_M_PR_BAT.WK_CNT         IS '�۾��Ǽ�'   ;  
COMMENT ON COLUMN TB_CM_M_PR_BAT.TOT_CNT        IS '�ѰǼ�'   ;  
COMMENT ON COLUMN TB_CM_M_PR_BAT.MOD_USER       IS '������'         ;  
COMMENT ON COLUMN TB_CM_M_PR_BAT.MOD_DATE        IS '�����Ͻ�'           ;    
/*------------------------
* ����_H_�ü�_��ġ_����Ʈ_�Ÿ�
*-------------------------
*/
drop table TB_CM_H_PR_BAT_APTMM ;
 CREATE TABLE TB_CM_H_PR_BAT_APTMM 
(
BAT_YM              VARCHAR(6  ) NOT NULL,
BJD_SGG_CODE        VARCHAR(5  ) NOT NULL,
BJD_UMD_CODE        VARCHAR(5  ) NOT NULL,
YYYY                VARCHAR(4  ) NOT NULL,
MM                  VARCHAR(2  ) NOT NULL,
DD                  VARCHAR(6  ) NOT NULL, 
SEQ                 NUMBER(10)   NOT NULL,
TR_AMOUNT           VARCHAR(40 ) NOT NULL,
ST_YYYY             VARCHAR(4  ) NOT NULL,
LOAD_NAME           VARCHAR(40 ) NOT NULL,
LOAD_BONBUN_CODE    VARCHAR(5  ) NOT NULL,
LOAD_BUBUN_CODE     VARCHAR(5  ) NOT NULL,
LAOD_SGG_CODE       VARCHAR(5  ) NOT NULL,
LOAD_SNO            VARCHAR(2  ) NOT NULL,
LOAD_UP_CODE        VARCHAR(1  ) NOT NULL,
LOAD_CODE           VARCHAR(7  ) NOT NULL,
BJD_NAME            VARCHAR(40 ) NOT NULL,
BJD_BONBUN_CODE     VARCHAR(4  ) NOT NULL,
BJD_BUBUN_CODE      VARCHAR(4  ) NOT NULL,
BJD_JIBUN_CODE      VARCHAR(1  ) NOT NULL,
KAPT_NAME           VARCHAR(50 ) NOT NULL,
SN                  VARCHAR(14 ) NOT NULL,
AREA_UNIT           NUMBER(10,2) NOT NULL,
JIBUN               VARCHAR(10 ) NOT NULL,
AREA_CODE           VARCHAR(5  ) NOT NULL,
FLOOR               VARCHAR(4  ) NOT NULL,
MOD_USER	VARCHAR(10) NOT NULL ,
MOD_DATE	DATE DEFAULT     SYSDATE ,
CONSTRAINT  PK_TB_CM_H_PR_BAT_APTMM PRIMARY KEY (BAT_YM, BJD_SGG_CODE, BJD_UMD_CODE, YYYY, MM, DD, SEQ) 
);
COMMENT ON TABLE TB_CM_H_PR_BAT_APTMM      IS '����_H_�ü�_��ġ_����Ʈ_�Ÿ�';
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.BAT_YM             IS '��ȸ���'					 ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.TR_AMOUNT          IS '�ŷ��ݾ�(����)'     ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.ST_YYYY            IS '����⵵'          ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.YYYY               IS '��'              ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.LOAD_NAME          IS '���θ�'           ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.LOAD_BONBUN_CODE   IS '���θ�ǹ�����ȣ�ڵ�' ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.LOAD_BUBUN_CODE    IS '���θ�ǹ��ι�ȣ�ڵ�' ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.LAOD_SGG_CODE      IS '���θ�ñ����ڵ�'    ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.LOAD_SNO           IS '���θ��Ϸù�ȣ�ڵ�'   ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.LOAD_UP_CODE       IS '���θ����������ڵ�'   ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.LOAD_CODE          IS '���θ��ڵ�'         ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.BJD_NAME           IS '������'           ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.BJD_BONBUN_CODE    IS '�����������ڵ�'      ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.BJD_BUBUN_CODE     IS '�������ι��ڵ�'      ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.BJD_SGG_CODE       IS '�������ñ����ڵ�'    ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.BJD_UMD_CODE       IS '���������鵿�ڵ�'    ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.BJD_JIBUN_CODE     IS '�����������ڵ�'      ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.KAPT_NAME           IS '����Ʈ'           ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.MM                 IS '��'              ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.DD                 IS '��'              ;    
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.SEQ                IS '������'              ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.SN                 IS '�Ϸù�ȣ'          ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.AREA_UNIT          IS '�������'          ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.JIBUN              IS '����'             ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.AREA_CODE          IS '�����ڵ�'          ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.FLOOR              IS '��'              ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.MOD_USER						IS '������'           ;
COMMENT ON COLUMN TB_CM_H_PR_BAT_APTMM.MOD_DATE						IS '�����Ͻ�'          ;
