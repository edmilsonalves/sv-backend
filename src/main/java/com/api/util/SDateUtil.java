package com.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;

/**
 * Classe utilitÃ¡ria para datas dos projetos do CRM
 */
public class SDateUtil {

	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String MM_YYYY = "MM/yyyy";
	public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	public static final String FMT_DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";
	public static final String FMT_DD_MM_YYYY_HH_MM_SEM_FORMATACAO = "ddMMyyyyHHmm";
	public static final String FMT_DD_MM_YYYY = "dd/MM/yyyy";
	public static final String FMT_HH_MM_SS = "HH:mm:ss";

	public static final String FMT_ISO_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String FMT_ISO_DATE = "yyyy-MM-";

	private SDateUtil() {
	}

	public static String getDateTimeToString(Date data) {
		DateFormat dateFormat = new SimpleDateFormat(FMT_DD_MM_YYYY_HH_MM);
		return dateFormat.format(data);
	}

	public static String getDateTimeToStringSemFormatacao(Date data) {
		DateFormat dateFormat = new SimpleDateFormat(FMT_DD_MM_YYYY_HH_MM_SEM_FORMATACAO);
		return dateFormat.format(data);
	}

	public static String getDateToString(Date data, String mask) {
		DateFormat dateFormat = new SimpleDateFormat(mask);
		return dateFormat.format(data);
	}

	public static String getDateToString(Date data) {
		DateFormat dateFormat = new SimpleDateFormat(FMT_DD_MM_YYYY);
		return dateFormat.format(data);
	}

	public static String getTimeToString(Date data) {
		DateFormat dateFormat = new SimpleDateFormat(FMT_HH_MM_SS);
		return dateFormat.format(data);
	}

	public static Date subtrairDias(Date data, Integer numeroDias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - numeroDias);
		return calendar.getTime();
	}

	public static Date subtrairHoras(Date data, Integer numeroHora) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - numeroHora);
		return calendar.getTime();
	}

	public static Date adicionarHoras(Date data, Integer numeroHora) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + numeroHora);
		return calendar.getTime();
	}

	public static Date adicionarDias(Date data, Integer numeroDias) {
		if (numeroDias != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + numeroDias);
			return calendar.getTime();
		}
		return null;
	}

	public static Date zerarHora(Date data) {
		if (data == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date setarHora(Date data, int hora, int minuto, int segundo) {
		if (data == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, hora);
		calendar.set(Calendar.MINUTE, minuto);
		calendar.set(Calendar.SECOND, segundo);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getFormattedDate(final Date date, final String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean compareDates(final Date date1, final Date date2) {
		SimpleDateFormat sdf = new SimpleDateFormat(FMT_DD_MM_YYYY);
		return sdf.format(date1).equals(sdf.format(date2));
	}

	public static String toDateTimeIsoString(final Date date) {
		if (date == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat(FMT_ISO_DATE_TIME);
		return sdf.format(date);
	}

	public static String toDateIsoString(final Date date) {
		if (date == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat(FMT_ISO_DATE);
		return sdf.format(date);
	}

	/**
	 * Calcula a quantidade de meses entre duas datas
	 *
	 * @param dataInicio data inicio
	 * @param dataFim    dtata fim
	 * @return Integer a quantidade de meses entre as duas datas
	 */
	public static Integer calcularDiferencaEmMeses(Date dataInicio, Date dataFim) {
		if (SUtils.isNull(dataInicio) || SUtils.isNull(dataFim)) {
			return 0;
		}

		Months months = Months.monthsBetween(
				new DateTime(dataInicio.getTime()).withField(DateTimeFieldType.dayOfMonth(), 1),
				new DateTime(dataFim.getTime()).withField(DateTimeFieldType.dayOfMonth(), 1));
		return months.getMonths();
	}

	/**
	 * Retorna false se a data fim for menor que a data inicio, desprezando horas e
	 * minutos.<br/>
	 * Retorna true caso ao menos uma das datas seja nula ou caso a data fim for
	 * igual ou maior que a data inicio.
	 *
	 * @param dataInicio data inicio
	 * @param dataFim    dtata fim
	 * @return
	 */
	public static boolean isValidDateRange(Date dataInicio, Date dataFim) {
		return !(dataInicio != null && dataFim != null
				&& DateTimeComparator.getInstance(DateTimeFieldType.dayOfMonth()).compare(dataInicio, dataFim) > 0);
	}

	/**
	 * Converte a string de data no formato dd/MM/yyyy para java.util.Date.
	 *
	 * @param data a data a ser convertida
	 * @return a data
	 */
	public static Date converteData(String data) {
		if (data != null) {
			return DateTimeFormat.forPattern(DD_MM_YYYY).parseDateTime(data).toDate();
		}
		return null;
	}

	/**
	 * Converte a string de data no formato dd/MM/yyyy HH:mm:ss para java.util.Date.
	 *
	 * @param dataHora a data e hora a ser convertida
	 * @return a data
	 */
	public static Date converteDataHora(String dataHora) {
		if (dataHora != null) {
			return DateTimeFormat.forPattern(DD_MM_YYYY_HH_MM_SS).parseDateTime(dataHora).toDate();
		}
		return null;
	}

	/**
	 * Formata a data no formato dd/MM/yyyy
	 *
	 * @param data a data a ser formatada
	 * @return a data formatada
	 */
	public static String formataData(Date data) {
		return DateTimeFormat.forPattern(DD_MM_YYYY).print(data.getTime());
	}

	/**
	 * Formata a data no formato dd/MM/yyyy HH:mm:ss
	 *
	 * @param data a data a ser formatada
	 * @return a data formatada
	 */
	public static String formataDataHora(Date data) {
		return DateTimeFormat.forPattern(DD_MM_YYYY_HH_MM_SS).print(data.getTime());
	}

	/**
	 * Verifica se uma string pode ser uma data.
	 *
	 * @param pData String no formato de data dd/MM/yyyy
	 */
	public static boolean isDataValida(String pData) {
		try {
			DateTimeFormat.forPattern(DD_MM_YYYY).parseLocalDate(pData);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Verifica se uma string pode ser uma data.
	 *
	 * @param pData String no formato de data MM/yyyy
	 */
	public static boolean isMesAnoValido(String pData) {
		try {
			DateTimeFormat.forPattern(MM_YYYY).parseLocalDate(pData);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Adiciona um nÃºmero de dias Ã  data informada.
	 * 
	 * @param data a data a ser modificada
	 * @param dias quantidade de dias
	 * @return nova data com a soma dos dias
	 */
	public static Date adicionaDias(Date data, int dias) {
		DateTime dtOrg = new DateTime(data);
		return dtOrg.plusDays(dias).toDate();
	}
}