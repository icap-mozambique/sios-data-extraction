/**
 * 
 */
package icap.columbia.org.mz.service.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.io.Files;

import icap.columbia.org.mz.mapper.Data;
import icap.columbia.org.mz.mapper.Event;
import icap.columbia.org.mz.mapper.TrackedEntityInstance;

/**
 * The utility class for data export (SIOS TEMPLATE)
 * 
 * @author Simone Fernando, 20/01/2021
 *
 */
public class PoiXLSUtil {

	Workbook workbook;

	private static final Logger logger = LogManager.getLogger(PoiXLSUtil.class);

	public PoiXLSUtil() {
		workbook = new XSSFWorkbook();
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> trackedEntityProperties = new LinkedHashMap() {
		{
			put("ID", "Tracker_Id");
			put("ORGANISATION_UNITY", "Organisation_Unity_name");
			put("DATA_INTERNAMENTO", "Data_Internamento");
			put("PmDlN847dyb", "IUCOVID19");
			put("Q3Apqd6UHkP", "EPINumber");
			put("x0OUK6B5KyT", "Nome");
			put("vvhCSddmMgC", "Apelido");
			put("sf0zfkywIrW", "Sexo");
			put("GGP5wBUWGYW", "Idade");
			put("MmqxiK1oOd7", "Nacionalidade");
			put("cmbKffjktBJ", "Outra_nacionalidade");
			put("uFhC4RDFv3D", "Raca");
			put("zgfmMXcVoNI", "Outra_Raca");
			put("TZ4804Xae0j", "Escolaridade");
			put("XiKRuIh41RP", "Profissional_saúde");
			put("eouNOg5RGA5", "Profissao");
			put("yVkO3ENKD1W", "Municipio");
			put("WlByFisAmXE", "Bairro");
			put("XrNBfs9K1HK", "Rua_Avenida");
			put("vRbXXINtLjR", "Quarteirao");
			put("ZBd69UaL1wI", "Numero_Casa");
			put("EHCenY4syPl", "Pontos_referencia_perto_de");
			put("K5LUUbleRha", "Deficiencia");
			put("WxtAYR93AR7", "Qual_deficiencia");
			put("bpybo3Z7OcH", "Telefone");
			put("MB0OeSXjBPr", "NUM_Telefone_alternativo");
			put("S2iF7cEVheM", "Email");
		}
	};

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> generalInformationProperties = new LinkedHashMap() {
		{
			put("kTCQu6R357x", "Numero_amostra");
			put("oXlbmREaQJR", "Data_colheita_primeira_amostra_positiva");
			put("XormPO9uICo", "Diagnostico_principal");
			put("ZdSC5IM5fD1", "Data_primeiros_sintomas");
			put("dxiDnhScjha", "Provenencia");
			put("XSqtK74QEtd", "Nome_US");
			put("Bi6pmm5lWJU", "Data_Admissão_US");
			put("srfk3ibV79h", "Outa_Provenencia");
			put("fc8JPq238BJ", "Aleitamento_Materno");
			put("hdblxOOjACh", "Aleitamento_Materno_Exclusivo_ate_6_meses");
			put("wnTwST4769S", "Idade_desmane");
			put("nwbk1evGm4Y", "Vacinacao_Adequada_para_idade");
			put("VRpksihgESQ", "Peso_nascer_KG");
			put("WIvc5zehHpn", "Idade_Gestacional");
			put("faKTCZGjPjq", "Mulher_gravida_ou_parto_ultimos_42_dias");
			put("wHXmkzZX5xB", "Data_ultima_menstruacao");
			put("JtjdOF0LNJb", "Gesta_G");
			put("oO6tmSDVahd", "Parto_P");
			put("dNYrpB5AQaw", "Abortos_A");
			put("Z9rhdxjjNin", "Filhos_Vivos_FV");
			put("Q2vo8xePnZd", "Filhos_Falecidos_FF");
			put("Swvz9eX5pry", "Status_admissao");
			put("JEyNlVMKD1x", "Data_esperada_parto_se_gravida");
			put("Vi6MRY36wLe", "Dias_pos_parto");
			put("wYMlDT49L7p", "Amamentacao_pos_parto");
		}
	};

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> notaEntradaProperties = new LinkedHashMap() {
		{
			put("xS79wRIQuI2", "Historia_febre");
			put("j1ELJa42nBx", "Data_inicio_febre");
			put("v8Yj4drbtt7", "Mantem_sintoma_febre_admissao");
			put("Cdb7AmEgsWO", "Data_fim_febre");
			put("h2Rvlmek5oU", "Tosse");
			put("ExIzZUp2gJ1", "Data_inicio_tosse");
			put("k1aV7n4LUNA", "Prevalece_sintoma_tosse");
			put("zmhdGFyV8xA", "Data_fim_tosse");
			put("EQJ3PkCe7wk", "Tosse_com_escarro");
			put("HUsxy98sMeG", "Data_inicio_tosse_escarro");
			put("cYK5W18eZsS", "Prevalece_tosse_escarro");
			put("OLQdi6KZbbQ", "Data_fim_tosse_escarro");
			put("zSENaB5TnUS", "Tosse_escarro_sangue_hemoptise");
			put("T4P5esZsT6U", "Data_inicio_tosse_escarro_sangue_hemoptise");
			put("TVVc3ZSSdtP", "Prevalece_tosse_escarro_sangue_hemoptise");
			put("OldQz2FQpaO", "Data_fim_tosse_escarro_sangue_hemoptise");
			put("Va39cfpDte7", "Dor_garganta_Odinofagia");
			put("WWq1KiVyTBd", "Data_inicio_dor_garganta");
			put("ynOhtgh51zF", "Prevalece_dor_garganta");
			put("kOQiDqHwYv0", "Data_fim_dor_garganta");
			put("RvKHwcrGOWe", "Secrecao_nasal_rinorreia");
			put("tH7GDxZbKiH", "Data_inicio_secrecao_nasal");
			put("DQCRitaEvcN", "Prevalece_secrecao_nasal");
			put("yDx2fw4VJ8n", "Data_fim_secrecao_nasal");
			put("AhsUCGnwIFa", "Dor_ouvido");
			put("FEv5ADhhZpV", "Data_inicio_dor_ouvido");
			put("asQEmkEfazX", "Prevalece_dor_ouvido");
			put("LBuIChN2yCO", "Data_fim_dor_ouvido");
			put("pOHaxq6rR1M", "Sibilos");
			put("qfxhnYanFS4", "Data_inicio_sibilos");
			put("lrP2t4a7lcU", "Prevalece_sintoma_sibilos");
			put("eaNR1Jm8oIS", "Data_fim_sibilos");
			put("ETSokzLE4xy", "Dor_peito_Torax");
			put("w0CA4jtrCJ4", "Data_inicio_dor_peito");
			put("Xh2H7w3RgDb", "Prevalece_dor_peito");
			put("JJ4oiiH40pE", "Data_fim_dor_peito");
			put("VEK90IHuL8Y", "Dores_musculares_mialgia");
			put("rvxJUhjQZUz", "Data_inicio_dores_musculares");
			put("eyWk9zUlJiO", "Prevalece_Dor_muscular");
			put("VDCkZpCx8E8", "Data_fim_dor_muscular");
			put("I43h2vmaYKu", "Dor_articulacoeses_artralgia");
			put("a40fYCFAB1a", "Data_inicio_dor_articulasoes");
			put("v5uDLMxIIHU", "Prevalece_dor_articulacoes");
			put("V1Kpueh3uL0", "Data_fim_dor_articulacoes");
			put("ckGS9o4YLIT", "Fadiga_Mal_estar");
			put("kXT13dbIyzS", "Data_inicio_fadiga_mal_estar");
			put("Y2O7HvAj47J", "Prevalece_fadiga_mal_estar");
			put("KGDHq0VYPwS", "Data_fm_fadiga_mal_estar");
			put("TNiRLtuICUB", "Falta_ar_dispneia");
			put("nSsFflH0N7q", "Data_inicio_falta_ar");
			put("wzBiowFZpM3", "Prevalece_falta_ar");
			put("hus26OxjNpR", "Data_fim_falta_ar");
			put("ihsnFQOqcx2", "Tiragem_parede_toraxica_inferior");
			put("o7amyFv458H", "Data_inicio_tiragem_parede_toraxica_inferior");
			put("K3R5yqlUB6E", "Prevalece_sintoma_tiragem_parede_toraxica_inferior");
			put("XrykpgK72Uh", "Data_fim_sintoma_tiragem_parede_toraxica_inferior");
			put("VY9qbxosI1y", "Dor_cabeca");
			put("jsKDHlLIhjb", "Data_inicio_dor_cabeca");
			put("fBFn4X3rGrv", "Prevalece_dor_cabeca");
			put("L5aAGSB1rbR", "Data_fim_dor_cabeca");
			put("FWDsP2hG8ci", "Consciencia_alterada_confusao");
			put("nnKNaxoyWJY", "Pimeiro_episosio");
			put("NHuqJk0t3Zs", "Data_inicio_consciencia_alterada_confusao");
			put("uf7DQVCUwZR", "Prevalece_consciencia_alterada_confusao");
			put("XxmAJcqVeWP", "Data_fim_consciencia_alterada_confusao");
			put("poYYJnCBuSM", "Convulsoes");
			put("Vmp0QXLLddL", "Quantas_convulsoes");
			put("LRkvPlHx4Cp", "Data_inicio_convulsoes");
			put("p8NK4Xm4rEA", "Prevalece_sintomas_convulsoes");
			put("FxByRMjleIo", "Data_fim_convulsoes");
			put("eMuNSC7B5Zn", "Dor_abdominal");
			put("ij3NuuJk2mo", "Data_inicio_dor_abdominal");
			put("EE1gZbOj3ID", "Prevalece_dor_abdominal");
			put("dNqGdqmeZoG", "Data_fim_dor_abdominal");
			put("gaQ4oZl44XQ", "Vomitos_Nausea");
			put("I0NDmhX24SW", "Data_inicio_vomito_nausea");
			put("cPxFMiQoLNj", "Prevalece_simtoma_vomito_nausea");
			put("gZMR8aMJ7hI", "Data_fim_vomito_nausea");
			put("XETIFypnJm1", "Diarreia");
			put("Sb5jcH72LpF", "Data_inicio_diarreia");
			put("XSD7KbFy3qZ", "Prevalece_simtoma_diarreia");
			put("zAOgF5ivsXf", "Data_fim_simtoma_diarreia");
			put("IyR9z6fzK5F", "Conjuntivite");
			put("N1IgJnmfQAL", "Data_inicio_conjuntivite");
			put("HzT2z14qsgI", "Prevalece_simtoma_conjuntivite");
			put("wmzXKvSchS2", "Data_fim_simtoma_conjuntivite");
			put("c3ZKV9ySlZ5", "Erupcao_cutanea");
			put("cqMnUuygcut", "Data_inicio_erupcao_cutanea");
			put("I0koV1ci6FY", "Prevalece_simtoma_erupcao_cutanea");
			put("x4mivCCthIA", "Data_fim_erupcao_cutanea");
			put("zuRuZWJplxq", "Calafrios_intensos");
			put("BvFh5KgxlCi", "Data_inicio_calafrios_intensos");
			put("fEHFk3SGNu3", "Prevalece_calafrios_intensos");
			put("xUlrrySe317", "Data_fim_calafrios_intensos");
			put("ncPF8Bod4VZ", "Anosmia");
			put("FBsSyhOZ0f7", "Data_inicio_Anosmia");
			put("zJ5cgIh6hJn", "Prevalece_simtoma_anosmia");
			put("FHVckRQvOF4", "Data_fim_simtoma_anosmia");
			put("QQ5ZAaWwk6P", "ageusia_falta_paladar");
			put("P9yFEZTIGWj", "Data_inicio_ageusia_falta_paladar");
			put("xvGH54aKMaW", "Prevalece_ageusia_falta_paladar");
			put("twBCy4GxhXw", "Data_fim_ageusia_falta_paladar");
			put("c2AFoqX0K7o", "Linfadenopatia");
			put("CuULAxbQrfC", "Localizacap_Linfadenopatia");
			put("tcdDwd7EscV", "Sangramento_hemorragia");
			put("PVhevkUwozw", "Data_inicio_sangramento_hemorragia");
			put("lEA7B75kkIA", "Prevalece_simtoma_sangramento_hemorragia");
			put("ETnEyJ370tT", "Data_fim_simtoma_sangramento_hemorragia");
			put("SFcl7C4AalQ", "Outro_local_sangramento");
			put("ES5lyUTjb3c", "Perdas_hematicas");
			put("CgCT28q4dhq", "Data_inicio_perdas_hematicas");
			put("gLr7WND8rLW", "Prevalece_simtoma_perdas_hematicas");
			put("NHeCwexnSFh", "Data_fim_perdas_hematicas");
			put("tlBsIMcOw8s", "Perdas_liquidos");
			put("ZQBDOdXNf2U", "Data_inicio_perdas_liquidos");
			put("ZNINpnZEc9C", "Prevalece_simtoma_perdas_liquidos");
			put("pqTBqs0vzkW", "Data_fim_simtoma_perdas_liquidos");
			put("rtnOxKk3xML", "Visao_turva");
			put("rqK2dLAlMTP", "Data_inicio_visao_turva");
			put("IpJ2l22I3n9", "Prevalece_visao_turva");
			put("LysKaR8tn86", "Data_fim_visao_turva");
			put("hv93kwq2mQl", "Dor_epigastrio");
			put("wfWdvRsH5xg", "Data_inicio_dor_epigastrio");
			put("IUzbsIHwT0H", "Prevalece_dor_epigastrio");
			put("wBfrrywLlvn", "Data_fim_dor_epigastrio");
			put("k2m11ukjt06", "Ausencia_movimentos_fetais");
			put("P5XRp3wH7QG", "Data_inicio_ausencia_movimentos_fetais");
			put("mHY5oncTNHH", "Prevalece_ausencia_movimentos_fetais");
			put("tpuESf06wTK", "Data_fim_simtoma_ausencia_movimentos_fetais");
			put("OEKhYwNn1DE", "Contracoes_uterinas");
			put("fvqzChyersU", "Data_inicio_Contracoes_uterinas");
			put("hfiaDM31bG2", "Prevalece_simtoma_Contracoes_uterinas");
			put("IuxHHUZAauv", "Data_fim_Contracoes_uterinas");
			put("jbNmaZQRclx", "Frequencia_cardiofetal");

			put("a4DjV9wEwZ0", "Sinais_vitais");
			put("sfQ6izbOqIz", "Peso_Kg");
			put("iMDDdIPoSUp", "Altura_cm");
			put("gs8ne2a4ZVW", "Comprimento_cm");
			put("kKDwdLQl3p8", "IMC_Kg/m2");
			put("EbHmv5Co98Z", "Diagnostico_principal_entrada");
		}
	};

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> dadosEpidemiologicosSinaisAdmissaoProperties = new LinkedHashMap() {
		{
			put("g0zZdKvepGh", "Historia_viagem_lugar_infecção_COVID19");
			put("qV1NmVWdRQc", "Contato_próximo_caso_confirmado_Covid19_enquanto_paciente_assintomatico");
			put("DWp3lCCjpft", "Presenca_estabelecimento_Saude_manejo_casos_covid19");
			put("tfoxOsZwrL9", "Presenca_laboratorio_manipula_amostras_covid19");
			put("oBfoobhWO1C", "Contato_direto_animais_paises_covid19");
			put("wFNmdcqrHwl", "Temperatura_GRAUS_CELSIUS");
			put("vy3cV6OTXlS", "FC_BPM");
			put("RTkvWVXQbsK", "FR_RPM");
			put("ncHtdA3m1nk", "Desidratacao");
			put("MNJczwMAxd7", "Tipo_Desidratacao");
			put("zcdHekMYh2Z", "PA_Sistolica_MMHG");
			put("nhyWzuc5AKB", "PA_diastolica_MMHG");
			put("EnyjsRM05sk", "Preenchimento_capilar_maior_2_segundos");
			put("pauaEY9n1EB", "SaO2_PERCENTAGEM_Ar_ambiente");
			put("ikrM7pYKSSh", "SaO2_PERCENTAGEM_oxigenioterapia");
			put("OEKhYwNn1DE", "Contracoess_uterinas");
			put("szoDEltqPI4", "Frequencia_cardiofetal");

		}
	};

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> factoresRiscoComorbidadesProperties = new LinkedHashMap() {
		{
			put("SkjIwyc70Pn", "Doenca_cardiaca_cronica_incluindo_congenita");
			put("tHSUNNsKi4x", "Qual_doenca_cardiaca_incluindo_congenita");
			put("zYVH21dZWYW", "Tratamento_doenca_cardiaca_incluindo_congenita");
			put("qnCN5oli40c", "Hipertensao_HPA");
			put("fQTCxeMAO6D", "Tratamento_Hipertencao_HPA");
			put("bSjdEfNAiYe", "Obesidade");
			put("UwNQAFPh8G6", "IMC");
			put("uHDUs3HbNaV", "DPOC");
			put("JqaByNxs1hx", "Asma");
			put("juotYmm6oLZ", "Diabetes");
			put("aYDg4qHWtQO", "Diabetes_COM_Complicacoes");
			put("TFHn6aDnlB0", "Doença_renal_cronica");
			put("yo7GBRn8sxY", "Distubios_reumatologicos");
			put("KNrroKCjpb8", "Doenca_hepatica");
			put("HjylyqgGOgq", "Tipo_doenca_hepatica");
			put("bSm4qi2Mpwb", "Doenca_neurologica_cronica");
			put("H26pUzQpoxI", "Tipo_doenca_neurologica_cronica");
			put("GJdv5hZObEj", "Demencia");
			put("jWTV6I3eImg", "Desnutricao");
			put("jrQ4WgvbIsO", "Fumante");
			put("poBD5ByMWVk", "Cozinha_com_lenha_ou_carvao");
			put("KQU0CDXZ5kY", "Cozinha_com_lenha_carvao_dentro_casa");
			put("H19MQrc7phZ", "Cozinha_com_lenha_carvao_fora_casa");
			put("y6ypSEJpFsW", "Neoplasia_maligna");
			put("GfHnws2vITQ", "Qual_neoplasma_maligno");
			put("NG0bYoF6zuG", "Doenca_hematologica_cronica");
			put("ozFniHldHhq", "Qual_doenca_hematologica_cronica");
			put("pNRUFSypJ1Z", "Tuberculose_Activa");
			put("xOZDiynmWhD", "Tipo_localizacao_Tuberculose_Activa");
			put("C9ncf0gaPsO", "Localizacao_extrapulmonar_Tuberculose_Activa");
			put("czqJ3iHAAmx", "Tipo_resistencia_Tuberculose_Activa");
			put("oDscYLbv4Qj", "Em_tratamento_TB");
			put("QKgBkb27IfL", "Data_inicio_tratamento_TB");
			put("JJZTrZhwA72", "HIV_SIDA");
			put("CfXwO6IGiQV", "Stagio_HIV_SIDA");
			put("E7AYxzG4XKJ", "EM_TARV");
			put("sbTf4pagH42", "Outro_factor_risco_relevante");
			put("V3HAl39mQER", "Especifique_outro_factor_risco_relevante");

		}
	};

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> partoDuranteInternamentoProperties = new LinkedHashMap() {
		{
			put("PU1sGlJEt7n", "Tipo_parto");
			put("cGlwFd4nGlT", "Complicacoes_gravidez_parto");
			put("ZkgXHeXHgPS", "Descricao_complicacao_gravidez_parto");
			put("LdaqGbWaFdS", "Obito_materno");
			put("OBK5m7mTgZ4", "Causa_obito_materno");
			put("NMqATQwPov3", "Nado_vivo");
			put("aXHbpSbxtKu", "APGAR_1_minuto");
			put("b6e6P9QPrMK", "APGAR_5_minuto");
			put("Mx2ZuA28RpZ", "Peso_nascimento");
			put("nwBCeCbyiEI", "Obito_neonatal");
			put("fVB6Y84TxLt", "Causa_obito_neonatal");
		}
	};

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> evolucaoClinicaProperties = new LinkedHashMap() {
		{
			put("ID", "Tracker_Id"); // link to sheet one
			put("NzT1OUSR7MS", "Febre_Calafrios");
			put("s1NLeh3qrYu", "Tosse");
			put("Gm7t92lbJYU", "Producao_escarro");
			put("EJfh3TyFe2R", "Dor_garganta");
			put("hvArqKzVE6u", "Corrimento_nasal_rinorreia");
			put("sEdGudMfeEu", "Dor_peito");
			put("r6TrojfBLs9", "Dores_musculares_Mialgia");
			put("h91UVEDdmbR", "Dor_articulacoes_Artralgia");
			put("VzxLrsZHKQ8", "Fadiga_Mal_estar_Prostacao");
			put("eeCxAxWntRH", "Falta_ar_dispneia");
			put("JYRt4BQRR8w", "Dor_cabeca");
			put("P61y1eFCPna", "Alteracoo_consciencia");
			put("CIT8OnY4bfT", "Dor_Abdominal");
			put("wCeKSYCmsa6", "Vomitos_Nausea");
			put("qWX9XRhvhKP", "Diarreia");
			put("yltNOZLzoxZ", "Anosmia_ageusia");
			put("XgDJ4BNWvBR", "Outros");
			put("dhrKpnA3aIE", "Temperatura_CELSIUS");
			put("pgyYZxxvbWJ", "Saturacao_periferica_oxigenio");
			put("VOa1bVO3Dq3", "Frequencia_respiratoria");
			put("CG09zUvIg5s", "Frequencia_Cardiaca");
			put("WLB3z1AyDKV", "TA_sistolica");
			put("IUxjraMa55J", "TA_diastolica");
			put("szoDEltqPI4", "Frequencia_cardiofetal");
			put("RtLgmX6gFPK", "HIV");
			put("ImxJY4Cha24", "CD4");
			put("enOEqcTwRAM", "Carga_Viral");
			put("XLpvzdhIQE8", "Hemograma");
			put("eGMddAxkoPF", "Hemoglobina_gL_g_dL");
			put("e89rZyU4lDC", "Leucocitos_totais");
			put("ms9REBychUz", "Neutrofilos_absoluto");
			put("gEWKXTm6rWJ", "Linfocitos_absoluto");
			put("WRJQH3qkuUv", "Plaquetas_absoluto");
			put("tfbLnmA1XYp", "Função_hepatica");
			put("Z4apv4wpwlF", "ALT");
			put("ornafpES2QI", "AST");
			put("ndrZ0vxjPJK", "Bilirrubina_total");
			put("fT1kgDVltJn", "LDH");
			put("mfiP6jIVcYL", "Proteinas_T");
			put("OSuJdnBWc5Y", "EXAM_LABORATORIO_Albumina");
			put("Okff6b1kV3Y", "Funcao_Renal");
			put("CqrQJJjakAk", "Ureia_mol_L_mg_dL");
			put("k5gCkhLxMYO", "Creatinina_mol_L_mg_dL");
			put("oYXoWYSifuL", "Sodio");
			put("wj58uqtHGUY", "Potassio");
			put("PCpebGKCD0N", "PCR_SARS_COV_2_Controle_cura");
			put("hfWFT6X1CiM", "Radiografia_torax_realizada");
			put("SdEmy0QNtRV", "Data_radiografia_torax_realizada");
			put("UzZNhhKn44O", "Havia_infiltrado_presente_radiografia_torax");
			put("ECIM6PHiYOp", "Provas_coagulacao");
			put("CKyMqzSmfzR", "D_dímeros");
			put("mfggulNBkbK", "PTT");
			put("SS4Wm7w1DPr", "PT");
			put("ovY3ZlZj2A5", "Fibrina");
			put("xKooW7OOREd", "Gasometria_Arterial_venosa");
			put("uGgpTXvHyFF", "PH");
			put("W6NtUSBN2YC", "PaO2");
			put("QG96KVDdvzm", "PaCO2");
			put("NQUy3s6jOz1", "HCO3");
			put("t1khy7F3TPy", "SaO2");
			put("S4TGJuUWh2u", "Provas_TB");
			put("oTzWq7Y43om", "Genxpert");
			put("NFzoNfZNuRG", "Exames_laboratorio_bk");
			put("mz9kAbyqaaO", "Cultura");
			put("yLhGDuSdCZq", "Ecografia_realizada");
			put("MmyzOAJQGsm", "Data_realizacao_ultrassom");
			put("Z6EqBBwEDyp", "Descricao_achados_ultrassom");
			put("vsEysjPZUOR", "ECG_realizado");
			put("pCuPJ4MDyxr", "Data_realizacao_ECG");
			put("CR0qllZVCMN", "Descricao_achados_ECG");
			put("dhjgZtm0xNl", "Glicemia");
			put("TlyBE3cv0lB", "Outros_Exames");
			put("ph114ptZmW8", "Oxigenioterapia");
			put("CR2NoyRxuXB", "Ventilacao_Mecanica");
			put("hmUToMpJNMC", "Dias_ventilação_mecanica");
			put("ATPtNO5fI66", "Transfusao_hemoderivados");
			put("hBlBCtgKG18", "Pleurotomia");
			put("fqsd5OM3is7", "Intubacao");
			put("nNfncqe8hYU", "Dialise_peritoneal");
			put("Dj1IKmAGC1Q", "CPAP_nasal");
			put("SiYyIS9bfSv", "Outros_procedimentos");
			put("l3sSZceACE0", "Antibioterapia");
			put("iwi1Oh4yutP", "Antipireticos");
			put("fKzGSfuqmbn", "Corticoides");
			put("rYAZ3I7Iqev", "Tocolise_se_gravida");
			put("OW3FS8GWkgI", "Maturacao_pulomnar_corticosteroides");
			put("HCqsP3NxZou", "Controle_radiologico_realizado");
			put("OvqflHbT1tP", "Data_controle_radiologico_realizado");
			put("wFHYvVU43gj", "Como_estavam_lesoes_evolutivamente");
			put("uhkaubv0oay", "Enfermaria");
			put("Z6P1oHhbTm1", "Cuidados_Intensivos");

		}
	};

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> cardexMedicaoProperties = new LinkedHashMap() {
		{
			put("ID", "Tracker_Id"); // link to sheet one
			put("Lt6Jg7go1bS", "Medicacao");
			put("OUeUs4a86FQ", "Outra_medicacao");
			put("zBMxtcVEK1m", "Dosagem_medicacao");
			put("Gn92f4ZwhCw", "Via_medicacao");
			put("GVFcQlM5oKe", "Frequencia_medicacao");
			put("nDHgJUpXpTH", "Hora_medicacao");
			put("fLZrYb1crOi", "Estado_medicacao");

		}
	};

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> cardexCuidadoEnfermagemProperties = new LinkedHashMap() {
		{
			put("ID", "Tracker_Id"); // link to sheet one
			put("S49Fe3Oxayr", "Cuidado_enfermagem");
			put("cgp4urmMI3Q", "Outro_Cuidado_enfermagem");
			put("FYb9ivD2NtE", "Dose");
			put("jZJrHRaFNkC", "Frequencia");
			put("IBSFCpTy2rc", "Hora");
			put("NyJm36LuSSB", "Estado");
		}
	};

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> diarioEnfermagemProperties = new LinkedHashMap() {
		{
			put("ID", "Tracker_Id"); // link to sheet one
			put("nKcfVqN2o3k", "Hora");
			put("ye7C5JEl0FS", "Notas");
		}
	};

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> desfechoProperties = new LinkedHashMap() {
		{

			put("l9TQ2wlchPT", "Tipo_desfecho");
			put("EbJz3LNcUGg", "trasferido_para");
			put("EKa7yKd6kU8", "razao_transferencia");
			put("DVp62S7PCYy", "Outra_razao");
			put("qAvcx6Yml8f", "directo");
			put("dVWwhmIbjTN", "Causa_directa");
			put("O7dRu68I3o5", "indirecto");
			put("nbIRrquHjf9", "Causa_indirecta");
			put("pFpHikY2O78", "Medico_assistente");
			put("Fj2vgKEIjyX", "Data_desfecho");
			put("fcguSv3MSD2", "PCR_SARS_COV_2");

		}
	};

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private Map<String, String> eventsProperties = new LinkedHashMap() {
		{
			put(Constants.PROGRAM_STAGE_ID_INFORMACAO_GERAL, "Informações Gerais");
			put(Constants.PROGRAM_STAGE_ID_NOTA_ENTRADA_HISTORIA_DOENCA_ACTUAL,
					"Nota de entrada (História da doença actual)");
			put(Constants.PROGRAM_STAGE_ID_DADOS_EPIDEMIOLOGICOS_SINAIS_ADMISSAO,
					"Dados Epidemiológicos, Sinais na admissão");
			put(Constants.PROGRAM_STAGE_ID_FACTOR_RISCO_COMORBIDADES, "Factores de riscos ou comorbidades");
			put(Constants.PROGRAM_STAGE_ID_PARTO_DURANTE_INTERNAMENTO, "Parto durante o internamento");
			put(Constants.PROGRAM_STAGE_ID_EVOLUCAO_CLINICA_ANALITICA_TRATAMENTO_SEGMENTO,
					"Evolução clínica, Analítica, Tratamento, Seguimento do paciente");
			put(Constants.PROGRAM_STAGE_ID_CARDEX_MEDICAO, "CARDEX - Medicação");
			put(Constants.PROGRAM_STAGE_ID_CARDEX_CUIDADO_ENFERMAGEM, "CARDEX - Cuidados de Enfermagem");
			put(Constants.PROGRAM_STAGE_ID_DIARIO_ENFERMAGEM, "Diário de Enfermagem");
			put(Constants.PROGRAM_STAGE_ID_DESFECHO, "Desfecho");
		}
	};

	/**
	 * 
	 * @param data   - the parsed data
	 * @param stages - List of included stage data (pass NULL to include all stages)
	 * @return byte[] file of data
	 * @throws IOException
	 */
	public byte[] exportXLS(Data data, List<String> stages) throws IOException {

		// Default and nome repeated sheet
		writeDefaultSheet(workbook, data, stages);

		// Repeated Sheets
		if (stages == null)
			stages = Arrays.asList(Constants.PROGRAM_STAGE_ID_EVOLUCAO_CLINICA_ANALITICA_TRATAMENTO_SEGMENTO,
					Constants.PROGRAM_STAGE_ID_CARDEX_MEDICAO, Constants.PROGRAM_STAGE_ID_CARDEX_CUIDADO_ENFERMAGEM,
					Constants.PROGRAM_STAGE_ID_DIARIO_ENFERMAGEM);

		for (String stage : stages)
			if (stage.equals(Constants.PROGRAM_STAGE_ID_EVOLUCAO_CLINICA_ANALITICA_TRATAMENTO_SEGMENTO)
					|| stage.equals(Constants.PROGRAM_STAGE_ID_CARDEX_MEDICAO)
					|| stage.equals(Constants.PROGRAM_STAGE_ID_CARDEX_CUIDADO_ENFERMAGEM)
					|| stage.equals(Constants.PROGRAM_STAGE_ID_DIARIO_ENFERMAGEM))
				writeOtherSheet(workbook, data, stage, eventsProperties.get(stage));

		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		workbook.write(bao);
		byte file[] = bao.toByteArray();

		// Closing the workbook
		workbook.close();

		logger.info("Exported to Byte[] successfuly");

		return file;
	}

	/**
	 * write data to given file name on the disk
	 * 
	 * @param file     - the byte[] exported data
	 * @param fileName - the file name without extention (include all relative path
	 *                 to DISK)
	 * @throws IOException
	 */
	public void writeToFile(byte[] file, String fileName) throws IOException {
		// Write the output to a file
		File ficheiro = new File(fileName);
		ficheiro.setReadable(true);
		ficheiro.setExecutable(true);
		ficheiro.setWritable(true);

		Files.write(file, ficheiro);

		logger.info("File: " + fileName + " created successfuly");
	}

	private void writeDefaultSheet(Workbook workbook2, Data data, List<String> stages) {
		Sheet sheet = workbook.createSheet("PACIENTE");

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(getHeaderFont());

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create cellHeader
		int i = 0;
		for (Map.Entry<String, String> entry : trackedEntityProperties.entrySet()) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(entry.getValue().toUpperCase());
			cell.setCellStyle(headerCellStyle);
			i++;
		}

		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_INFORMACAO_GERAL)) {
			for (Map.Entry<String, String> entry : generalInformationProperties.entrySet()) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(entry.getValue().toUpperCase());
				cell.setCellStyle(headerCellStyle);
				i++;
			}
		}

		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_NOTA_ENTRADA_HISTORIA_DOENCA_ACTUAL)) {
			for (Map.Entry<String, String> entry : notaEntradaProperties.entrySet()) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(entry.getValue().toUpperCase());
				cell.setCellStyle(headerCellStyle);
				i++;
			}
		}

		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_DADOS_EPIDEMIOLOGICOS_SINAIS_ADMISSAO)) {
			for (Map.Entry<String, String> entry : dadosEpidemiologicosSinaisAdmissaoProperties.entrySet()) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(entry.getValue().toUpperCase());
				cell.setCellStyle(headerCellStyle);
				i++;
			}
		}

		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_FACTOR_RISCO_COMORBIDADES)) {
			for (Map.Entry<String, String> entry : factoresRiscoComorbidadesProperties.entrySet()) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(entry.getValue().toUpperCase());
				cell.setCellStyle(headerCellStyle);
				i++;
			}
		}

		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_PARTO_DURANTE_INTERNAMENTO)) {
			for (Map.Entry<String, String> entry : partoDuranteInternamentoProperties.entrySet()) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(entry.getValue().toUpperCase());
				cell.setCellStyle(headerCellStyle);
				i++;
			}
		}

		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_DESFECHO)) {
			for (Map.Entry<String, String> entry : desfechoProperties.entrySet()) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(entry.getValue().toUpperCase());
				cell.setCellStyle(headerCellStyle);
				i++;
			}
		}

		if (data != null && data.getTrackedEntityInstances() != null) {
			for (int j = 0; j < data.getTrackedEntityInstances().size(); j++) {
				Row dataRow = sheet.createRow(j + 1);
				TrackedEntityInstance trackedEntityInstance = data.getTrackedEntityInstances().get(j);

				int k = 0;
				for (Map.Entry<String, String> entry : trackedEntityProperties.entrySet()) {
					Cell cell = dataRow.createCell(k);
					if (k == 0) {
						cell.setCellValue(trackedEntityInstance.getTrackedEntityInstance());
					} else if (k == 1) {
						cell.setCellValue(SIOSUtil.getOrganisationUnityById(trackedEntityInstance.getOrgUnit())
								.getDisplayFormName());
					} else if (k == 2) {
						Date enrrolDate = trackedEntityInstance.getEnrollments() != null
								? trackedEntityInstance.getEnrollments().get(0).getEnrollmentDate()
								: null;
						cell.setCellValue(parse(enrrolDate, "yyyy-MM-dd"));
					} else {
						cell.setCellValue(trackedEntityInstance.getAttributeValueById(entry.getKey()));
					}
					k++;
				}

				if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_INFORMACAO_GERAL)) {
					Event generalInfoEvent = trackedEntityInstance
							.getSingleEventByProgramStageIdAndTrackedEntityInstanceId(
									Constants.PROGRAM_STAGE_ID_INFORMACAO_GERAL);
					if (generalInfoEvent != null) {
						for (Map.Entry<String, String> entry : generalInformationProperties.entrySet()) {
							Cell cell = dataRow.createCell(k);
							cell.setCellValue(generalInfoEvent.getDataValueById(entry.getKey()));
							k++;
						}
					}
				}

				if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_NOTA_ENTRADA_HISTORIA_DOENCA_ACTUAL)) {
					Event notaEntradaEvent = trackedEntityInstance
							.getSingleEventByProgramStageIdAndTrackedEntityInstanceId(
									Constants.PROGRAM_STAGE_ID_NOTA_ENTRADA_HISTORIA_DOENCA_ACTUAL);
					if (notaEntradaEvent != null) {
						for (Map.Entry<String, String> entry : notaEntradaProperties.entrySet()) {
							Cell cell = dataRow.createCell(k);
							cell.setCellValue(notaEntradaEvent.getDataValueById(entry.getKey()));
							k++;
						}
					}
				}

				if (stages == null
						|| stages.contains(Constants.PROGRAM_STAGE_ID_DADOS_EPIDEMIOLOGICOS_SINAIS_ADMISSAO)) {
					Event dadosEpidemiologicosEvent = trackedEntityInstance
							.getSingleEventByProgramStageIdAndTrackedEntityInstanceId(
									Constants.PROGRAM_STAGE_ID_DADOS_EPIDEMIOLOGICOS_SINAIS_ADMISSAO);
					if (dadosEpidemiologicosEvent != null) {
						for (Map.Entry<String, String> entry : dadosEpidemiologicosSinaisAdmissaoProperties
								.entrySet()) {
							Cell cell = dataRow.createCell(k);
							cell.setCellValue(dadosEpidemiologicosEvent.getDataValueById(entry.getKey()));
							k++;
						}
					}
				}

				if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_FACTOR_RISCO_COMORBIDADES)) {
					Event factorRiscoEvent = trackedEntityInstance
							.getSingleEventByProgramStageIdAndTrackedEntityInstanceId(
									Constants.PROGRAM_STAGE_ID_FACTOR_RISCO_COMORBIDADES);
					if (factorRiscoEvent != null) {
						for (Map.Entry<String, String> entry : factoresRiscoComorbidadesProperties.entrySet()) {
							Cell cell = dataRow.createCell(k);
							cell.setCellValue(factorRiscoEvent.getDataValueById(entry.getKey()));
							k++;
						}
					}
				}

				if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_PARTO_DURANTE_INTERNAMENTO)) {
					Event partoDuranteInternamentoEvent = trackedEntityInstance
							.getSingleEventByProgramStageIdAndTrackedEntityInstanceId(
									Constants.PROGRAM_STAGE_ID_PARTO_DURANTE_INTERNAMENTO);
					if (partoDuranteInternamentoEvent != null) {
						for (Map.Entry<String, String> entry : partoDuranteInternamentoProperties.entrySet()) {
							Cell cell = dataRow.createCell(k);
							cell.setCellValue(partoDuranteInternamentoEvent.getDataValueById(entry.getKey()));
							k++;
						}
					}
				}

				if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_DESFECHO)) {
					Event desfechoEvent = trackedEntityInstance
							.getSingleEventByProgramStageIdAndTrackedEntityInstanceId(
									Constants.PROGRAM_STAGE_ID_DESFECHO);
					if (desfechoEvent != null) {
						for (Map.Entry<String, String> entry : desfechoProperties.entrySet()) {
							Cell cell = dataRow.createCell(k);
							cell.setCellValue(desfechoEvent.getDataValueById(entry.getKey()));
							k++;
						}
					}
				}
			}
		}

		// Resize all columns to fit the content size
		int totalColumm = trackedEntityProperties.size();
		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_INFORMACAO_GERAL)) {
			totalColumm += generalInformationProperties.size();
		}

		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_NOTA_ENTRADA_HISTORIA_DOENCA_ACTUAL)) {
			totalColumm += notaEntradaProperties.size();
		}

		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_DADOS_EPIDEMIOLOGICOS_SINAIS_ADMISSAO)) {
			totalColumm += dadosEpidemiologicosSinaisAdmissaoProperties.size();
		}

		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_FACTOR_RISCO_COMORBIDADES)) {
			totalColumm += factoresRiscoComorbidadesProperties.size();
		}

		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_PARTO_DURANTE_INTERNAMENTO)) {
			totalColumm += partoDuranteInternamentoProperties.size();
		}

		if (stages == null || stages.contains(Constants.PROGRAM_STAGE_ID_DESFECHO)) {
			totalColumm += desfechoProperties.size();
		}

		for (int s = 0; s < totalColumm; s++) {
			sheet.autoSizeColumn(s);
		}

	}

	/**
	 * 
	 * @param workbook2 - The XLS Workbook
	 * @param data      - extracted data
	 * @param stage     - The Stage ID (Program Stage ID)
	 * @param sheetName - The Stage Name (this value is used to set the sheet name)
	 */
	private void writeOtherSheet(Workbook workbook2, Data data, String stage, String sheetName) {
		if (stage != null) {
			Sheet sheet = workbook.createSheet(sheetName.toUpperCase());

			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(getHeaderFont());

			// Create a Row
			Row headerRow = sheet.createRow(0);

			// Create cellHeader and dataRows
			int i = 0;
			int k;
			List<TrackedEntityInstance> trackedEntityInstances = data.getTrackedEntityInstances();

			if (stage.equals(Constants.PROGRAM_STAGE_ID_EVOLUCAO_CLINICA_ANALITICA_TRATAMENTO_SEGMENTO)) {
				// cellHeader
				for (Map.Entry<String, String> entry : evolucaoClinicaProperties.entrySet()) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(entry.getValue().toUpperCase());
					cell.setCellStyle(headerCellStyle);
					i++;
				}

				// data rows
				if (trackedEntityInstances != null) {
					for (TrackedEntityInstance trackedEntityInstance : trackedEntityInstances) {
						List<Event> events = trackedEntityInstance
								.getMultipleEventByProgramStageIdAndTrackedEntityInstanceId(
										Constants.PROGRAM_STAGE_ID_EVOLUCAO_CLINICA_ANALITICA_TRATAMENTO_SEGMENTO);
						if (events != null) {
							for (int row = 0; row < events.size(); row++) {
								Event event = events.get(row);
								Row dataRow = sheet.createRow(row + 1);
								k = 0;
								for (Map.Entry<String, String> entry : evolucaoClinicaProperties.entrySet()) {
									Cell cell = dataRow.createCell(k);
									if (k == 0) {
										cell.setCellValue(trackedEntityInstance.getTrackedEntityInstance());
									} else {
										cell.setCellValue(event.getDataValueById(entry.getKey()));
									}
									k++;
								}
							}
						}
					}
				}

			} else if (stage.equals(Constants.PROGRAM_STAGE_ID_CARDEX_MEDICAO)) {
				// cellHeader
				for (Map.Entry<String, String> entry : cardexMedicaoProperties.entrySet()) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(entry.getValue().toUpperCase());
					cell.setCellStyle(headerCellStyle);
					i++;
				}

				// data rows
				if (trackedEntityInstances != null) {
					for (TrackedEntityInstance trackedEntityInstance : trackedEntityInstances) {
						List<Event> events = trackedEntityInstance
								.getMultipleEventByProgramStageIdAndTrackedEntityInstanceId(
										Constants.PROGRAM_STAGE_ID_CARDEX_MEDICAO);
						if (events != null) {
							for (int row = 0; row < events.size(); row++) {
								Event event = events.get(row);
								Row dataRow = sheet.createRow(row + 1);
								k = 0;
								for (Map.Entry<String, String> entry : cardexMedicaoProperties.entrySet()) {
									Cell cell = dataRow.createCell(k);
									if (k == 0) {
										cell.setCellValue(trackedEntityInstance.getTrackedEntityInstance());
									} else {
										cell.setCellValue(event.getDataValueById(entry.getKey()));
									}
									k++;
								}
							}
						}
					}
				}

			} else if (stage.equals(Constants.PROGRAM_STAGE_ID_CARDEX_CUIDADO_ENFERMAGEM)) {
				// cellHeader
				for (Map.Entry<String, String> entry : cardexCuidadoEnfermagemProperties.entrySet()) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(entry.getValue().toUpperCase());
					cell.setCellStyle(headerCellStyle);
					i++;
				}

				// data rows
				if (trackedEntityInstances != null) {
					for (TrackedEntityInstance trackedEntityInstance : trackedEntityInstances) {
						List<Event> events = trackedEntityInstance
								.getMultipleEventByProgramStageIdAndTrackedEntityInstanceId(
										Constants.PROGRAM_STAGE_ID_CARDEX_CUIDADO_ENFERMAGEM);
						if (events != null) {
							for (int row = 0; row < events.size(); row++) {
								Event event = events.get(row);
								Row dataRow = sheet.createRow(row + 1);
								k = 0;
								for (Map.Entry<String, String> entry : cardexCuidadoEnfermagemProperties.entrySet()) {
									Cell cell = dataRow.createCell(k);
									if (k == 0) {
										cell.setCellValue(trackedEntityInstance.getTrackedEntityInstance());
									} else {
										cell.setCellValue(event.getDataValueById(entry.getKey()));
									}
									k++;
								}
							}
						}
					}
				}

			} else if (stage.equals(Constants.PROGRAM_STAGE_ID_DIARIO_ENFERMAGEM)) {
				// cellHeader
				for (Map.Entry<String, String> entry : diarioEnfermagemProperties.entrySet()) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(entry.getValue().toUpperCase());
					cell.setCellStyle(headerCellStyle);
					i++;
				}

				// data rows
				if (trackedEntityInstances != null) {
					for (TrackedEntityInstance trackedEntityInstance : trackedEntityInstances) {
						List<Event> events = trackedEntityInstance
								.getMultipleEventByProgramStageIdAndTrackedEntityInstanceId(
										Constants.PROGRAM_STAGE_ID_DIARIO_ENFERMAGEM);
						if (events != null) {
							for (int row = 0; row < events.size(); row++) {
								Event event = events.get(row);
								Row dataRow = sheet.createRow(row + 1);
								k = 0;
								for (Map.Entry<String, String> entry : diarioEnfermagemProperties.entrySet()) {
									Cell cell = dataRow.createCell(k);
									if (k == 0) {
										cell.setCellValue(trackedEntityInstance.getTrackedEntityInstance());
									} else {
										cell.setCellValue(event.getDataValueById(entry.getKey()));
									}
									k++;
								}
							}
						}
					}
				}
			}

			// Resize all columns to fit the content size
			int totalColumm = 0;
			if (stage.equals(Constants.PROGRAM_STAGE_ID_EVOLUCAO_CLINICA_ANALITICA_TRATAMENTO_SEGMENTO)) {
				totalColumm = evolucaoClinicaProperties.size();
			} else if (stage.equals(Constants.PROGRAM_STAGE_ID_CARDEX_MEDICAO)) {
				totalColumm = cardexMedicaoProperties.size();
			} else if (stage.equals(Constants.PROGRAM_STAGE_ID_CARDEX_CUIDADO_ENFERMAGEM)) {
				totalColumm = cardexCuidadoEnfermagemProperties.size();
			} else if (stage.equals(Constants.PROGRAM_STAGE_ID_DIARIO_ENFERMAGEM)) {
				totalColumm = diarioEnfermagemProperties.size();
			}

			for (int s = 0; s < totalColumm; s++) {
				sheet.autoSizeColumn(s);
			}
		}

	}

	/**
	 * This method return the Header Font (apply all header text Values)
	 * 
	 * @return Font
	 */
	private Font getHeaderFont() {
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		return headerFont;
	}

	/**
	 * 
	 * @param date
	 * @param pattern (example:dd/MM/yyyy)
	 * @return
	 */
	public String parse(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String dateR = simpleDateFormat.format(date);
			return dateR;
		} else {
			return null;
		}
	}

}
