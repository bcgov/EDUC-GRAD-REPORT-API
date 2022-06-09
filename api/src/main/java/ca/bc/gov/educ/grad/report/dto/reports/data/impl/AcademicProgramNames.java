/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static ca.bc.gov.educ.grad.report.model.common.support.VerifyUtils.trimSafe;
import static java.lang.String.format;

/**
 * Map with Academic program names.
 *
 * TODO: Move into code set services.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AcademicProgramNames {

    private static final Map<String, String> PROGRAM_NAMES;

    static {
        final Map<String, String> map = new HashMap<>();
        map.put("AB", "Autobody");
        map.put("AC", "Agribusiness");
        map.put("AE", "Aircraft Maintenance Engineering");
        map.put("AG", "Agriculture");
        map.put("AI", "Air Travel and Tourism");
        map.put("AP", "Auto Parts");
        map.put("AS", "HS/Adult Services");
        map.put("AT", "Aviation Technology");
        map.put("BB", "Boat Building");
        map.put("BE", "Business Education");
        map.put("CA", "Commercial Art");
        map.put("CD", "Computer Assisted Design/Drafting");
        map.put("CH", "HS/Children's Services");
        map.put("CJ", "Carpentry/Joinery");
        map.put("CM", "Choral Music");
        map.put("CR", "Community Recreation");
        map.put("CS", "Computer Science");
        map.put("DA", "Dental Assisting");
        map.put("DC", "CAREERS IN DESIGN");
        map.put("DP", "Data Processing");
        map.put("DR", "Drafting");
        map.put("EL", "Electronics");
        map.put("EY", "Electricity");
        map.put("FA", "Fashion Merchandising");
        map.put("FD", "Fashion Design");
        map.put("FM", "Forestry Management");
        map.put("FO", "Forestry");
        map.put("FS", "HS/Family Services");
        map.put("FY", "Foundry");
        map.put("GM", "General Mechanics");
        map.put("GR", "Graphics");
        map.put("HA", "Hairdressing");
        map.put("HF", "Hospitality/Foods");
        map.put("HM", "Heavy Duty Mechanics");
        map.put("HO", "Horticulture");
        map.put("HS", "HS/Health Care Services");
        map.put("HT", "Hospitality/Tourism");
        map.put("ID", "Interior Design");
        map.put("IM", "Instrumental Music");
        map.put("MA", "MF/Machinist");
        map.put("MI", "Mining");
        map.put("MW", "MF/Millwright");
        map.put("OI", "Oilfield Worker");
        map.put("PS", "Asia Pacific Studies");
        map.put("PT", "Plastics Technology");
        map.put("BC", "International Baccalaureate");
        map.put("BD", "International Baccalaureate");
        map.put("SM", "MF/Sheet Metal");
        map.put("TH", "Theatre");
        map.put("TV", "TV Production");
        map.put("WE", "MF/Welding");
        map.put("CF", "Cafeteria");
        map.put("TS", "Technology & Science");
        map.put("HU", "Human Services");
        map.put("AN", "Accounting");
        map.put("EN", "Entrepreneurship");
        map.put("IN", "Information Management");
        map.put("MK", "Marketing");
        map.put("SE", "Secretarial");
        map.put("LO", "Logging");
        map.put("SI", "Silviculture");
        map.put("MR", "Marine");
        map.put("AD", "Advanced Placement");
        map.put("CW", "Careers in Writing");
        map.put("CL", "Clerical");
        map.put("EM", "Electronic Music Composition");
        map.put("JR", "Journalism");
        map.put("OM", "Office Management");
        map.put("PC", "Publishing & Communication");
        map.put("SC", "Stagecraft");
        map.put("AR", "Art Gallery");
        map.put("BA", "Baking");
        map.put("CN", "Construction");
        map.put("CP", "Coaching Preparation");
        map.put("CT", "Communications Technology");
        map.put("CV", "Community Services");
        map.put("EG", "Engineering");
        map.put("ES", "Environmental Studies");
        map.put("FC", "Forestry & Silviculture");
        map.put("FN", "Fine Arts");
        map.put("LI", "Lithographics");
        map.put("LW", "Law");
        map.put("ME", "Media");
        map.put("MU", "Music");
        map.put("OC", "Outdoor Careers");
        map.put("RB", "Radio Broadcasting");
        map.put("RL", "Recreational Leadership");
        map.put("SN", "Sciences");
        map.put("TE", "Teaching");
        map.put("TN", "Technology");
        map.put("BI", "Biology");
        map.put("CE", "Chemistry");
        map.put("CO", "Computer Applications");
        map.put("ET", "Environmental Technology");
        map.put("FI", "Finance Management");
        map.put("MJ", "Media Journalism");
        map.put("MT", "Mechanics Technology");
        map.put("PE", "Power Engineering");
        map.put("PH", "Physics");
        map.put("PR", "Pacific Rim Studies");
        map.put("VP", "Visual/Performance Arts");
        map.put("CG", "Construction Technology");
        map.put("TM", "Tourism/Management");
        map.put("BM", "Business Management/Economics");
        map.put("DT", "Desktop Publishing");
        map.put("BN", "Business Computing");
        map.put("IC", "Instrumental/Choral Music");
        map.put("CU", "Classical Studies/Music");
        map.put("HI", "Health/Industrial Technologies");
        map.put("CX", "Career Explorations");
        map.put("TX", "Clothing & Textiles");
        map.put("CY", "Communication Arts");
        map.put("TC", "Technology Survey");
        map.put("NR", "Natural Resources");
        map.put("CI", "Child Development");
        map.put("SG", "Science & Engineering");
        map.put("RA", "Recording Arts & Science");
        map.put("CC", "Creative Arts");
        map.put("IS", "Instrumental Survey & Repair");
        map.put("EU", "European Studies/Tourism");
        map.put("HE", "Home Economics");
        map.put("AA", "Art Careers");
        map.put("SR", "Sports & Recreation");
        map.put("VA", "Video Arts");
        map.put("FH", "Fisheries");
        map.put("AL", "Applied Engineering Technology");
        map.put("RM", "Resource Management");
        map.put("PG", "Pre-engineering");
        map.put("MN", "Mining Technology");
        map.put("MM", "Metal/Mechanics");
        map.put("RC", "Recreation/Leisure Management");
        map.put("AV", "Architecture & Development");
        map.put("FV", "Food Services");
        map.put("DS", "Design/Publishing");
        map.put("DN", "Dance");
        map.put("FR", "First Nations Studies");
        map.put("PM", "Pre Employment");
        map.put("MD", "Media Technology");
        map.put("FZ", "Fashion Merchandising & Design");
        map.put("GC", "Graphic Communications");
        map.put("SA", "Studio Arts");
        map.put("VD", "Video Production");
        map.put("MS", "Medical Services");
        map.put("SS", "Social Services");
        map.put("AH", "Applied Humanities");
        map.put("AM", "Applied Mathematics");
        map.put("BF", "Business & Service Careers in French");
        map.put("BG", "Business Management/Entrepreneurship");
        map.put("BS", "Biological Sciences");
        map.put("BT", "Biotechnology");
        map.put("BU", "Business and Trades Careers");
        map.put("CB", "Cabinet Making/Millwork");
        map.put("DE", "Drafting/Electronics");
        map.put("ED", "Engineering & Design");
        map.put("FF", "Fashion");
        map.put("FK", "Food Preparation & Marketing");
        map.put("FP", "Fine Arts Production");
        map.put("GS", "Global Studies");
        map.put("HL", "Health Sciences");
        map.put("HP", "Human Performance");
        map.put("LB", "Log House Building");
        map.put("LM", "Library/Media Management");
        map.put("MC", "Musical Theatre");
        map.put("OF", "Office Careers");
        map.put("TO", "Technological Opportunities");
        map.put("TT", "Trades Technology");
        map.put("AJ", "Applied Sciences");
        map.put("EC", "Early Childhood Education");
        map.put("BR", "Business Administration");
        map.put("HC", "Hospitality/Catering");
        map.put("AO", "Auto Technician");
        map.put("CK", "Computer Systems Management");
        map.put("DM", "Drama");
        map.put("EF", "Environment, Fish & Forest");
        map.put("EH", "Engineering Technology");
        map.put("FB", "First Nations Management");
        map.put("FE", "First Nations Futures");
        map.put("GP", "Global Perspectives");
        map.put("HB", "Home-Based Business Studies");
        map.put("HV", "Heavy Equipment Operator");
        map.put("IT", "Integrated Sciences");
        map.put("IU", "International Business");
        map.put("JP", "Journalism & Publishing");
        map.put("LC", "Language/Culture/Tourism");
        map.put("LE", "Leadership Development");
        map.put("LH", "Library Science");
        map.put("LN", "Library Studies");
        map.put("LS", "Legal Services");
        map.put("MF", "Medical Care Technology");
        map.put("ML", "Modern Languages");
        map.put("MP", "Music Performance");
        map.put("OE", "Outdoor Education");
        map.put("PP", "Photography");
        map.put("RR", "Renewable Resources");
        map.put("SK", "Ski Resort Operations");
        map.put("SP", "Special Needs Work Study");
        map.put("ST", "Sports Administration");
        map.put("TL", "Computer Telecommunications & Networking");
        map.put("TP", "Transportation, Power & Energy");
        map.put("UA", "Adventure Tourism");
        map.put("TW", "Telecommunications & Networking");
        map.put("XS", "Career Exploration - Special Needs");
        map.put("AF", "Art/Fashion Design");
        map.put("BO", "Biology-Research & Development");
        map.put("GE", "Geosciences");
        map.put("AK", "Architectural Design");
        map.put("BZ", "Business Management");
        map.put("AQ", "Cooperative Transition");
        map.put("AU", "Automotive");
        map.put("AW", "Cooperative Education");
        map.put("AX", "Academic");
        map.put("AY", "Archaeology");
        map.put("AZ", "Applied Technology");
        map.put("DG", "Design Technology");
        map.put("EA", "Education and Child Services");
        map.put("EI", "Entertainment Industry Careers");
        map.put("EJ", "Environmental Recreational Industries");
        map.put("FG", "Food/Technology");
        map.put("FJ", "Fashion Arts and Design");
        map.put("FT", "Forestry/Technology");
        map.put("HD", "Health Services and Technology");
        map.put("HN", "Humanities");
        map.put("IA", "Independent-Directed");
        map.put("UB", "Applied Business Technology");
        map.put("IF", "Information Management Technology");
        map.put("KT", "Cooks Training");
        map.put("LA", "Academic Leadership");
        map.put("LF", "Life Skills");
        map.put("LG", "Legal Secretary");
        map.put("LT", "Literature");
        map.put("MG", "Marketing/Entrepreneurship");
        map.put("MO", "Mathematics Careers");
        map.put("OR", "Outdoor Recreation Resource Management");
        map.put("OT", "Office Technology");
        map.put("PA", "Performing Arts");
        map.put("PU", "Public Service");
        map.put("SB", "Secondary School Apprenticeship");
        map.put("SH", "Science and Humanities");
        map.put("SV", "Consumer Services");
        map.put("TA", "Tourism/Adventure");
        map.put("TB", "Theatre Company (Backstage)");
        map.put("TD", "Theatre Arts");
        map.put("TF", "Technical Orientation Program");
        map.put("TG", "Techno-Systems Management");
        map.put("TI", "Tourism");
        map.put("TJ", "Tourism-Recreation");
        map.put("TY", "Theatre Technology");
        map.put("WG", "Worksite Grad");
        map.put("WP", "Wood Products Manufacturing");
        map.put("YI", "Youth Internship");
        map.put("ZA", "Alternate Education");
        map.put("ZB", "Apprenticeship Exploration");
        map.put("EB", "Education");
        map.put("ZC", "Administrative Assistant");
        map.put("NA", "Agriculture & Natural Resources");
        map.put("ZV", "Art, Culture, Recreation & Sport");
        map.put("ZJ", "Athletics & Leisure Pursuits");
        map.put("ZO", "Automotive Merchandising");
        map.put("VT", "Automotive Technology");
        map.put("MB", "Broadcast Media");
        map.put("BV", "Business Ventures");
        map.put("ZZ", "Business, Finance, & Administration");
        map.put("ZE", "Careers in Communications");
        map.put("ZX", "Health");
        map.put("ZM", "Materials & Production Technology");
        map.put("ZY", "Natural & Applied Sciences");
        map.put("ZR", "Primary Industry");
        map.put("ZF", "Print Communications");
        map.put("ZS", "Trades, Transport & Equip. Operation");
        map.put("ZP", "Textiles, Fashion & Design");
        map.put("ZW", "Social Science, Ed., Gov. Services");
        map.put("ZT", "Sales & Service");
        map.put("ZL", "Culinary Arts");
        map.put("ZH", "Criminal Justice");
        map.put("ZI", "Commerce");
        map.put("ZG", "Computer Composition & Recording");
        map.put("ZD", "Computer Graphics");
        map.put("ZN", "Computer Multimedia");
        map.put("ZK", "Computer Studies");
        map.put("CQ", "Cook Training Apprenticeship");
        map.put("TZ", "Theatre Company");
        map.put("PL", "Peer Counselling");
        map.put("EV", "Environmental Science");
        map.put("MH", "Machinist/Metal Technology");
        map.put("TQ", "Technical & Service Careers");
        map.put("EK", "Electronic Design & Publishing");
        map.put("PD", "Performance Arts Production");
        map.put("SD", "Systems Integration");
        map.put("HZ", "Human Support Services");
        map.put("DB", "Design Communications");
        map.put("HK", "Human Kinetics & Recreation");
        map.put("MZ", "Media Studies");
        map.put("NS", "Natural Sciences");
        map.put("FL", "Forestry Management & Logging");
        map.put("GV", "Graphics & Visual Arts");
        map.put("FQ", "First Nation Art");
        map.put("JC", "Justice Careers");
        map.put("CZ", "Computer Technology");
        map.put("HG", "Hotel Management");
        map.put("PB", "Psychology");
        map.put("ER", "EMERGENCY RESPONSE TRAINING");
        map.put("VM", "VOCAL MUSIC");
        map.put("PW", "PRIMARY WOOD PROCESSING");
        map.put("SW", "SECONDARY WOOD PRODUCTS MANUFACTURING");
        map.put("EE", "ENTREPRENEURIAL STUDIES");
        map.put("JA", "joinery apprrenticeship");
        map.put("JB", "JEWELLERY ART");
        map.put("MQ", "Mentorship");
        map.put("FW", "French Language Careers");
        map.put("EQ", "Equestrian Studies");
        map.put("MY", "COMMUNICATIONS MEDIA/FINE ARTS");
        map.put("LD", "LAW ENFORCEMENT");
        map.put("IE", "INFORMATION TECHNOLOGY");
        map.put("EO", "EMPLOYABILITY SKILLS");
        map.put("MV", "MANFACTURING");
        map.put("EP", "ECO-SCIENCE");
        map.put("IG", "CHILDHOOD EDUCATION");
        map.put("EW", "EMPLOYMENT DEVELOPMENT");
        map.put("GT", "GENERAL TRADES APP. & SEC. COMPLETION");
        map.put("BH", "ELECTRICAL TRADES APP. & SEC. COMPLETION");
        map.put("BJ", "ENTERPRISE DEVELOPMENT");
        map.put("DD", "CAREER FOCUS FOR ADULT LEARNERS");
        map.put("FU", "FILM AND VIDEO PRODUCTION");
        map.put("FX", "FINE AND PERFORMING ARTS");
        map.put("GA", "ANIMATION SOCIO-CULTURELEE");
        map.put("GI", "GOLF INDUSTRY");
        map.put("VF", "FILM & VIDEO ANIMATION");
        map.put("YM", "MILITARY");
        map.put("ZU", "ABORIGINAL CULTURE AND ECONOMY");
        map.put("UC", "Automotive Service Technician");
        map.put("UD", "Carpentry");
        map.put("UE", "Computer Information Systems");
        map.put("UF", "Eco-Tourism");
        map.put("UG", "Electrical");
        map.put("UH", "Golf Club Management");
        map.put("UI", "Graphic Communications and Production");
        map.put("UJ", "Health and Human Services");
        map.put("UK", "Heavy Duty Commerical Transport Mechanic");
        map.put("UL", "Hospitality Administration");
        map.put("UM", "Info, Communication Tech. Service Tech.");
        map.put("UN", "Information Technology Specialist");
        map.put("UO", "Masonry");
        map.put("UP", "Outdoor Power Equipment");
        map.put("UQ", "Plumbing");
        map.put("UR", "Professional Cook Training");
        map.put("US", "Recreation Vehicle Service Technician");
        map.put("UT", "Retail Meat Processing");
        map.put("UU", "Welding");
        map.put("UV", "Wood Processing Technician");
        map.put("XA", "BUSINESS AND APPLIED BUSINESS");
        map.put("XB", "FINE ARTS, DESIGN AND MEDIA");
        map.put("XC", "FITNESS AND RECREATION");
        map.put("XD", "HEALTH AND HUMAN SERVICES");
        map.put("XE", "LIBERAL ARTS AND HUMANITIES");
        map.put("XF", "SCIENCE AND APPLIED SCIENCE");
        map.put("XG", "TOURISM, HOSPITALITY AND FOODS");
        map.put("XH", "TRADES AND TECHNOLOGY");

        PROGRAM_NAMES = Collections.unmodifiableMap(map);
    }

    /**
     * Returns the academic program name for the given code using an internal
     * map of codes to program names.
     *
     * @param code The code to look up in the map.
     * @return A non-null, trimmed, possibly empty string.
     */
    public static String getAcademicProgramName(final String code) {
        String programName = format("[%s] %s", code, trimSafe(PROGRAM_NAMES.get(code)));
        return programName;
    }
}
