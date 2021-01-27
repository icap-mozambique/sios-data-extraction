package icap.columbia.org.mz.service.util;

/**
 * 
 * @author Simone Fernando
 *
 */
public class Constants {

	public static final String ID_PROGRAM_FICHA_INTERNAMENTO_COVID_19 = "YkYCP9awaw7";
	public static final String ID_PROGRAM_FICHA_INVESTIGACAO_NOTIFICACAO_COVID_19 = "osq4XDYRBdB";
	public static final String ID_PROGRAM_FICHA_SEGMENTO_CASO_CONFIRMADO_COVID_19 = "EG4wcFOTjmv";
	public static final String ID_PROGRAM_FICHA_VIGILANCIA_ACTIVA_COVID_19 = "mRCRbd0Hfqb";

	//No Repeatable Stages
	public static final String PROGRAM_STAGE_ID_INFORMACAO_GERAL = "ifzxO5muNbU";
	public static final String PROGRAM_STAGE_ID_NOTA_ENTRADA_HISTORIA_DOENCA_ACTUAL = "cKzKguL2Ez5";
	public static final String PROGRAM_STAGE_ID_DADOS_EPIDEMIOLOGICOS_SINAIS_ADMISSAO = "kfvHXCjyCId";
	public static final String PROGRAM_STAGE_ID_FACTOR_RISCO_COMORBIDADES = "wSZ0HVo5RGC";
	public static final String PROGRAM_STAGE_ID_PARTO_DURANTE_INTERNAMENTO = "HHgPs0aycqE";
	public static final String PROGRAM_STAGE_ID_DESFECHO = "NvfvuGH1kjA";
	
	
	//Repeatable Stages
	public static final String PROGRAM_STAGE_ID_EVOLUCAO_CLINICA_ANALITICA_TRATAMENTO_SEGMENTO = "Ceo9FXo43fS";
	public static final String PROGRAM_STAGE_ID_CARDEX_MEDICAO = "graGX6qichw";
	public static final String PROGRAM_STAGE_ID_CARDEX_CUIDADO_ENFERMAGEM = "Uee2vcakk9k";
	public static final String PROGRAM_STAGE_ID_DIARIO_ENFERMAGEM = "zeikCB6j8ra";
	
	

	public static final String ID_OU_CABO_DELGADO = "jtbNkyjlMiW";
	public static final String ID_OU_GAZA = "N2EC0qBdqEd";
	public static final String ID_OU_INHAMBANE = "tvbFMrGCP2B";
	public static final String ID_OU_MANICA = "lgrWtt8CHwq";
	public static final String ID_OU_MAPUTO_CIDADE = "pbw2C4K3AOI";
	public static final String ID_OU_MAPUTO_PROVINCIA = "oGGez9trTkg";
	public static final String ID_OU_NAMPULA = "xwuEz9H0X47";
	public static final String ID_OU_NIASSA = "J90FkJ5BKOS";
	public static final String ID_OU_SOFALA = "IF5wtACmmeg";
	public static final String ID_OU_TETE = "RgZ7HNJe2WX";
	public static final String ID_OU_ZAMBEZIA = "OZYh6DpSqx4";

	public static final String PARAMETER_TRACKED_ENTITY_FIELDS = "fields=trackedEntityInstance,orgUnit,attributes[attribute,valueType,value,displayName],enrollments[trackedEntityInstance,enrollment,program,orgUnit,orgUnitName,enrollmentDate,events[programStage,trackedEntityInstance,eventDate,dataValues[dataElement,value]]";

	public static final int SIOS_ORGANIZATION_LEVEL_ID_PAIS = 1;
	public static final int SIOS_ORGANIZATION_LEVEL_ID_PROVINCIA = 2;
	public static final int SIOS_ORGANIZATION_LEVEL_ID_DISTRITO = 3;
	public static final int SIOS_ORGANIZATION_LEVEL_ID_UNIDADE_SANITARIA = 4;

}
