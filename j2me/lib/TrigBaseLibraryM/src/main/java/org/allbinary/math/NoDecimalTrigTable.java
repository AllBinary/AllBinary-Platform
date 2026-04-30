/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.math;

import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.math.MathUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;


public class NoDecimalTrigTable
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final NoDecimalTrigTable instance = new NoDecimalTrigTable();

    public static NoDecimalTrigTable getInstance()
    {
        return NoDecimalTrigTable.instance;
    }

    private final MathUtil mathUtil = MathUtil.getInstance();
    
    private final long[] noDecimalSin;
    private final long[] noDecimalCos;
    private final long[] noDecimalTan;

    private NoDecimalTrigTable()
    {
        final int TOTAL_ANGLE = (int) AngleFactory.getInstance().TOTAL_ANGLE;
        final long MAX_VALUE = ((long) Integer.MAX_VALUE);
        
        this.noDecimalSin = new long[TOTAL_ANGLE];
        this.noDecimalCos = new long[TOTAL_ANGLE];
        this.noDecimalTan = new long[TOTAL_ANGLE + 1];

        this.noDecimalSin[0] = 0;
        this.noDecimalSin[1] = 174;
        this.noDecimalSin[2] = 348;
        this.noDecimalSin[3] = 523;
        this.noDecimalSin[4] = 697;
        this.noDecimalSin[5] = 871;
        this.noDecimalSin[6] = 1045;
        this.noDecimalSin[7] = 1218;
        this.noDecimalSin[8] = 1391;
        this.noDecimalSin[9] = 1564;
        this.noDecimalSin[10] = 1736;
        this.noDecimalSin[11] = 1908;
        this.noDecimalSin[12] = 2079;
        this.noDecimalSin[13] = 2249;
        this.noDecimalSin[14] = 2419;
        this.noDecimalSin[15] = 2588;
        this.noDecimalSin[16] = 2756;
        this.noDecimalSin[17] = 2923;
        this.noDecimalSin[18] = 3090;
        this.noDecimalSin[19] = 3255;
        this.noDecimalSin[20] = 3420;
        this.noDecimalSin[21] = 3583;
        this.noDecimalSin[22] = 3746;
        this.noDecimalSin[23] = 3907;
        this.noDecimalSin[24] = 4067;
        this.noDecimalSin[25] = 4226;
        this.noDecimalSin[26] = 4383;
        this.noDecimalSin[27] = 4539;
        this.noDecimalSin[28] = 4694;
        this.noDecimalSin[29] = 4848;
        this.noDecimalSin[30] = 4999;
        this.noDecimalSin[31] = 5150;
        this.noDecimalSin[32] = 5299;
        this.noDecimalSin[33] = 5446;
        this.noDecimalSin[34] = 5591;
        this.noDecimalSin[35] = 5735;
        this.noDecimalSin[36] = 5877;
        this.noDecimalSin[37] = 6018;
        this.noDecimalSin[38] = 6156;
        this.noDecimalSin[39] = 6293;
        this.noDecimalSin[40] = 6427;
        this.noDecimalSin[41] = 6560;
        this.noDecimalSin[42] = 6691;
        this.noDecimalSin[43] = 6819;
        this.noDecimalSin[44] = 6946;
        this.noDecimalSin[45] = 7071;
        this.noDecimalSin[46] = 7193;
        this.noDecimalSin[47] = 7313;
        this.noDecimalSin[48] = 7431;
        this.noDecimalSin[49] = 7547;
        this.noDecimalSin[50] = 7660;
        this.noDecimalSin[51] = 7771;
        this.noDecimalSin[52] = 7880;
        this.noDecimalSin[53] = 7986;
        this.noDecimalSin[54] = 8090;
        this.noDecimalSin[55] = 8191;
        this.noDecimalSin[56] = 8290;
        this.noDecimalSin[57] = 8386;
        this.noDecimalSin[58] = 8480;
        this.noDecimalSin[59] = 8571;
        this.noDecimalSin[60] = 8660;
        this.noDecimalSin[61] = 8746;
        this.noDecimalSin[62] = 8829;
        this.noDecimalSin[63] = 8910;
        this.noDecimalSin[64] = 8987;
        this.noDecimalSin[65] = 9063;
        this.noDecimalSin[66] = 9135;
        this.noDecimalSin[67] = 9205;
        this.noDecimalSin[68] = 9271;
        this.noDecimalSin[69] = 9335;
        this.noDecimalSin[70] = 9396;
        this.noDecimalSin[71] = 9455;
        this.noDecimalSin[72] = 9510;
        this.noDecimalSin[73] = 9563;
        this.noDecimalSin[74] = 9612;
        this.noDecimalSin[75] = 9659;
        this.noDecimalSin[76] = 9702;
        this.noDecimalSin[77] = 9743;
        this.noDecimalSin[78] = 9781;
        this.noDecimalSin[79] = 9816;
        this.noDecimalSin[80] = 9848;
        this.noDecimalSin[81] = 9876;
        this.noDecimalSin[82] = 9902;
        this.noDecimalSin[83] = 9925;
        this.noDecimalSin[84] = 9945;
        this.noDecimalSin[85] = 9961;
        this.noDecimalSin[86] = 9975;
        this.noDecimalSin[87] = 9986;
        this.noDecimalSin[88] = 9993;
        this.noDecimalSin[89] = 9998;
        this.noDecimalSin[90] = 10000;
        this.noDecimalSin[91] = 9998;
        this.noDecimalSin[92] = 9993;
        this.noDecimalSin[93] = 9986;
        this.noDecimalSin[94] = 9975;
        this.noDecimalSin[95] = 9961;
        this.noDecimalSin[96] = 9945;
        this.noDecimalSin[97] = 9925;
        this.noDecimalSin[98] = 9902;
        this.noDecimalSin[99] = 9876;
        this.noDecimalSin[100] = 9848;
        this.noDecimalSin[101] = 9816;
        this.noDecimalSin[102] = 9781;
        this.noDecimalSin[103] = 9743;
        this.noDecimalSin[104] = 9702;
        this.noDecimalSin[105] = 9659;
        this.noDecimalSin[106] = 9612;
        this.noDecimalSin[107] = 9563;
        this.noDecimalSin[108] = 9510;
        this.noDecimalSin[109] = 9455;
        this.noDecimalSin[110] = 9396;
        this.noDecimalSin[111] = 9335;
        this.noDecimalSin[112] = 9271;
        this.noDecimalSin[113] = 9205;
        this.noDecimalSin[114] = 9135;
        this.noDecimalSin[115] = 9063;
        this.noDecimalSin[116] = 8987;
        this.noDecimalSin[117] = 8910;
        this.noDecimalSin[118] = 8829;
        this.noDecimalSin[119] = 8746;
        this.noDecimalSin[120] = 8660;
        this.noDecimalSin[121] = 8571;
        this.noDecimalSin[122] = 8480;
        this.noDecimalSin[123] = 8386;
        this.noDecimalSin[124] = 8290;
        this.noDecimalSin[125] = 8191;
        this.noDecimalSin[126] = 8090;
        this.noDecimalSin[127] = 7986;
        this.noDecimalSin[128] = 7880;
        this.noDecimalSin[129] = 7771;
        this.noDecimalSin[130] = 7660;
        this.noDecimalSin[131] = 7547;
        this.noDecimalSin[132] = 7431;
        this.noDecimalSin[133] = 7313;
        this.noDecimalSin[134] = 7193;
        this.noDecimalSin[135] = 7071;
        this.noDecimalSin[136] = 6946;
        this.noDecimalSin[137] = 6819;
        this.noDecimalSin[138] = 6691;
        this.noDecimalSin[139] = 6560;
        this.noDecimalSin[140] = 6427;
        this.noDecimalSin[141] = 6293;
        this.noDecimalSin[142] = 6156;
        this.noDecimalSin[143] = 6018;
        this.noDecimalSin[144] = 5877;
        this.noDecimalSin[145] = 5735;
        this.noDecimalSin[146] = 5591;
        this.noDecimalSin[147] = 5446;
        this.noDecimalSin[148] = 5299;
        this.noDecimalSin[149] = 5150;
        this.noDecimalSin[150] = 4999;
        this.noDecimalSin[151] = 4848;
        this.noDecimalSin[152] = 4694;
        this.noDecimalSin[153] = 4539;
        this.noDecimalSin[154] = 4383;
        this.noDecimalSin[155] = 4226;
        this.noDecimalSin[156] = 4067;
        this.noDecimalSin[157] = 3907;
        this.noDecimalSin[158] = 3746;
        this.noDecimalSin[159] = 3583;
        this.noDecimalSin[160] = 3420;
        this.noDecimalSin[161] = 3255;
        this.noDecimalSin[162] = 3090;
        this.noDecimalSin[163] = 2923;
        this.noDecimalSin[164] = 2756;
        this.noDecimalSin[165] = 2588;
        this.noDecimalSin[166] = 2419;
        this.noDecimalSin[167] = 2249;
        this.noDecimalSin[168] = 2079;
        this.noDecimalSin[169] = 1908;
        this.noDecimalSin[170] = 1736;
        this.noDecimalSin[171] = 1564;
        this.noDecimalSin[172] = 1391;
        this.noDecimalSin[173] = 1218;
        this.noDecimalSin[174] = 1045;
        this.noDecimalSin[175] = 871;
        this.noDecimalSin[176] = 697;
        this.noDecimalSin[177] = 523;
        this.noDecimalSin[178] = 348;
        this.noDecimalSin[179] = 174;
        this.noDecimalSin[180] = 0;
        this.noDecimalSin[181] = -174;
        this.noDecimalSin[182] = -348;
        this.noDecimalSin[183] = -523;
        this.noDecimalSin[184] = -697;
        this.noDecimalSin[185] = -871;
        this.noDecimalSin[186] = -1045;
        this.noDecimalSin[187] = -1218;
        this.noDecimalSin[188] = -1391;
        this.noDecimalSin[189] = -1564;
        this.noDecimalSin[190] = -1736;
        this.noDecimalSin[191] = -1908;
        this.noDecimalSin[192] = -2079;
        this.noDecimalSin[193] = -2249;
        this.noDecimalSin[194] = -2419;
        this.noDecimalSin[195] = -2588;
        this.noDecimalSin[196] = -2756;
        this.noDecimalSin[197] = -2923;
        this.noDecimalSin[198] = -3090;
        this.noDecimalSin[199] = -3255;
        this.noDecimalSin[200] = -3420;
        this.noDecimalSin[201] = -3583;
        this.noDecimalSin[202] = -3746;
        this.noDecimalSin[203] = -3907;
        this.noDecimalSin[204] = -4067;
        this.noDecimalSin[205] = -4226;
        this.noDecimalSin[206] = -4383;
        this.noDecimalSin[207] = -4539;
        this.noDecimalSin[208] = -4694;
        this.noDecimalSin[209] = -4848;
        this.noDecimalSin[210] = -5000;
        this.noDecimalSin[211] = -5150;
        this.noDecimalSin[212] = -5299;
        this.noDecimalSin[213] = -5446;
        this.noDecimalSin[214] = -5591;
        this.noDecimalSin[215] = -5735;
        this.noDecimalSin[216] = -5877;
        this.noDecimalSin[217] = -6018;
        this.noDecimalSin[218] = -6156;
        this.noDecimalSin[219] = -6293;
        this.noDecimalSin[220] = -6427;
        this.noDecimalSin[221] = -6560;
        this.noDecimalSin[222] = -6691;
        this.noDecimalSin[223] = -6819;
        this.noDecimalSin[224] = -6946;
        this.noDecimalSin[225] = -7071;
        this.noDecimalSin[226] = -7193;
        this.noDecimalSin[227] = -7313;
        this.noDecimalSin[228] = -7431;
        this.noDecimalSin[229] = -7547;
        this.noDecimalSin[230] = -7660;
        this.noDecimalSin[231] = -7771;
        this.noDecimalSin[232] = -7880;
        this.noDecimalSin[233] = -7986;
        this.noDecimalSin[234] = -8090;
        this.noDecimalSin[235] = -8191;
        this.noDecimalSin[236] = -8290;
        this.noDecimalSin[237] = -8386;
        this.noDecimalSin[238] = -8480;
        this.noDecimalSin[239] = -8571;
        this.noDecimalSin[240] = -8660;
        this.noDecimalSin[241] = -8746;
        this.noDecimalSin[242] = -8829;
        this.noDecimalSin[243] = -8910;
        this.noDecimalSin[244] = -8987;
        this.noDecimalSin[245] = -9063;
        this.noDecimalSin[246] = -9135;
        this.noDecimalSin[247] = -9205;
        this.noDecimalSin[248] = -9271;
        this.noDecimalSin[249] = -9335;
        this.noDecimalSin[250] = -9396;
        this.noDecimalSin[251] = -9455;
        this.noDecimalSin[252] = -9510;
        this.noDecimalSin[253] = -9563;
        this.noDecimalSin[254] = -9612;
        this.noDecimalSin[255] = -9659;
        this.noDecimalSin[256] = -9702;
        this.noDecimalSin[257] = -9743;
        this.noDecimalSin[258] = -9781;
        this.noDecimalSin[259] = -9816;
        this.noDecimalSin[260] = -9848;
        this.noDecimalSin[261] = -9876;
        this.noDecimalSin[262] = -9902;
        this.noDecimalSin[263] = -9925;
        this.noDecimalSin[264] = -9945;
        this.noDecimalSin[265] = -9961;
        this.noDecimalSin[266] = -9975;
        this.noDecimalSin[267] = -9986;
        this.noDecimalSin[268] = -9993;
        this.noDecimalSin[269] = -9998;
        this.noDecimalSin[270] = -10000;
        this.noDecimalSin[271] = -9998;
        this.noDecimalSin[272] = -9993;
        this.noDecimalSin[273] = -9986;
        this.noDecimalSin[274] = -9975;
        this.noDecimalSin[275] = -9961;
        this.noDecimalSin[276] = -9945;
        this.noDecimalSin[277] = -9925;
        this.noDecimalSin[278] = -9902;
        this.noDecimalSin[279] = -9876;
        this.noDecimalSin[280] = -9848;
        this.noDecimalSin[281] = -9816;
        this.noDecimalSin[282] = -9781;
        this.noDecimalSin[283] = -9743;
        this.noDecimalSin[284] = -9702;
        this.noDecimalSin[285] = -9659;
        this.noDecimalSin[286] = -9612;
        this.noDecimalSin[287] = -9563;
        this.noDecimalSin[288] = -9510;
        this.noDecimalSin[289] = -9455;
        this.noDecimalSin[290] = -9396;
        this.noDecimalSin[291] = -9335;
        this.noDecimalSin[292] = -9271;
        this.noDecimalSin[293] = -9205;
        this.noDecimalSin[294] = -9135;
        this.noDecimalSin[295] = -9063;
        this.noDecimalSin[296] = -8987;
        this.noDecimalSin[297] = -8910;
        this.noDecimalSin[298] = -8829;
        this.noDecimalSin[299] = -8746;
        this.noDecimalSin[300] = -8660;
        this.noDecimalSin[301] = -8571;
        this.noDecimalSin[302] = -8480;
        this.noDecimalSin[303] = -8386;
        this.noDecimalSin[304] = -8290;
        this.noDecimalSin[305] = -8191;
        this.noDecimalSin[306] = -8090;
        this.noDecimalSin[307] = -7986;
        this.noDecimalSin[308] = -7880;
        this.noDecimalSin[309] = -7771;
        this.noDecimalSin[310] = -7660;
        this.noDecimalSin[311] = -7547;
        this.noDecimalSin[312] = -7431;
        this.noDecimalSin[313] = -7313;
        this.noDecimalSin[314] = -7193;
        this.noDecimalSin[315] = -7071;
        this.noDecimalSin[316] = -6946;
        this.noDecimalSin[317] = -6819;
        this.noDecimalSin[318] = -6691;
        this.noDecimalSin[319] = -6560;
        this.noDecimalSin[320] = -6427;
        this.noDecimalSin[321] = -6293;
        this.noDecimalSin[322] = -6156;
        this.noDecimalSin[323] = -6018;
        this.noDecimalSin[324] = -5877;
        this.noDecimalSin[325] = -5735;
        this.noDecimalSin[326] = -5591;
        this.noDecimalSin[327] = -5446;
        this.noDecimalSin[328] = -5299;
        this.noDecimalSin[329] = -5150;
        this.noDecimalSin[330] = -5000;
        this.noDecimalSin[331] = -4848;
        this.noDecimalSin[332] = -4694;
        this.noDecimalSin[333] = -4539;
        this.noDecimalSin[334] = -4383;
        this.noDecimalSin[335] = -4226;
        this.noDecimalSin[336] = -4067;
        this.noDecimalSin[337] = -3907;
        this.noDecimalSin[338] = -3746;
        this.noDecimalSin[339] = -3583;
        this.noDecimalSin[340] = -3420;
        this.noDecimalSin[341] = -3255;
        this.noDecimalSin[342] = -3090;
        this.noDecimalSin[343] = -2923;
        this.noDecimalSin[344] = -2756;
        this.noDecimalSin[345] = -2588;
        this.noDecimalSin[346] = -2419;
        this.noDecimalSin[347] = -2249;
        this.noDecimalSin[348] = -2079;
        this.noDecimalSin[349] = -1908;
        this.noDecimalSin[350] = -1736;
        this.noDecimalSin[351] = -1564;
        this.noDecimalSin[352] = -1391;
        this.noDecimalSin[353] = -1218;
        this.noDecimalSin[354] = -1045;
        this.noDecimalSin[355] = -871;
        this.noDecimalSin[356] = -697;
        this.noDecimalSin[357] = -523;
        this.noDecimalSin[358] = -348;
        this.noDecimalSin[359] = -174;

        this.noDecimalCos[0] = 10000;
        this.noDecimalCos[1] = 9998;
        this.noDecimalCos[2] = 9993;
        this.noDecimalCos[3] = 9986;
        this.noDecimalCos[4] = 9975;
        this.noDecimalCos[5] = 9961;
        this.noDecimalCos[6] = 9945;
        this.noDecimalCos[7] = 9925;
        this.noDecimalCos[8] = 9902;
        this.noDecimalCos[9] = 9876;
        this.noDecimalCos[10] = 9848;
        this.noDecimalCos[11] = 9816;
        this.noDecimalCos[12] = 9781;
        this.noDecimalCos[13] = 9743;
        this.noDecimalCos[14] = 9702;
        this.noDecimalCos[15] = 9659;
        this.noDecimalCos[16] = 9612;
        this.noDecimalCos[17] = 9563;
        this.noDecimalCos[18] = 9510;
        this.noDecimalCos[19] = 9455;
        this.noDecimalCos[20] = 9396;
        this.noDecimalCos[21] = 9335;
        this.noDecimalCos[22] = 9271;
        this.noDecimalCos[23] = 9205;
        this.noDecimalCos[24] = 9135;
        this.noDecimalCos[25] = 9063;
        this.noDecimalCos[26] = 8987;
        this.noDecimalCos[27] = 8910;
        this.noDecimalCos[28] = 8829;
        this.noDecimalCos[29] = 8746;
        this.noDecimalCos[30] = 8660;
        this.noDecimalCos[31] = 8571;
        this.noDecimalCos[32] = 8480;
        this.noDecimalCos[33] = 8386;
        this.noDecimalCos[34] = 8290;
        this.noDecimalCos[35] = 8191;
        this.noDecimalCos[36] = 8090;
        this.noDecimalCos[37] = 7986;
        this.noDecimalCos[38] = 7880;
        this.noDecimalCos[39] = 7771;
        this.noDecimalCos[40] = 7660;
        this.noDecimalCos[41] = 7547;
        this.noDecimalCos[42] = 7431;
        this.noDecimalCos[43] = 7313;
        this.noDecimalCos[44] = 7193;
        this.noDecimalCos[45] = 7071;
        this.noDecimalCos[46] = 6946;
        this.noDecimalCos[47] = 6819;
        this.noDecimalCos[48] = 6691;
        this.noDecimalCos[49] = 6560;
        this.noDecimalCos[50] = 6427;
        this.noDecimalCos[51] = 6293;
        this.noDecimalCos[52] = 6156;
        this.noDecimalCos[53] = 6018;
        this.noDecimalCos[54] = 5877;
        this.noDecimalCos[55] = 5735;
        this.noDecimalCos[56] = 5591;
        this.noDecimalCos[57] = 5446;
        this.noDecimalCos[58] = 5299;
        this.noDecimalCos[59] = 5150;
        this.noDecimalCos[60] = 5000;
        this.noDecimalCos[61] = 4848;
        this.noDecimalCos[62] = 4694;
        this.noDecimalCos[63] = 4539;
        this.noDecimalCos[64] = 4383;
        this.noDecimalCos[65] = 4226;
        this.noDecimalCos[66] = 4067;
        this.noDecimalCos[67] = 3907;
        this.noDecimalCos[68] = 3746;
        this.noDecimalCos[69] = 3583;
        this.noDecimalCos[70] = 3420;
        this.noDecimalCos[71] = 3255;
        this.noDecimalCos[72] = 3090;
        this.noDecimalCos[73] = 2923;
        this.noDecimalCos[74] = 2756;
        this.noDecimalCos[75] = 2588;
        this.noDecimalCos[76] = 2419;
        this.noDecimalCos[77] = 2249;
        this.noDecimalCos[78] = 2079;
        this.noDecimalCos[79] = 1908;
        this.noDecimalCos[80] = 1736;
        this.noDecimalCos[81] = 1564;
        this.noDecimalCos[82] = 1391;
        this.noDecimalCos[83] = 1218;
        this.noDecimalCos[84] = 1045;
        this.noDecimalCos[85] = 871;
        this.noDecimalCos[86] = 697;
        this.noDecimalCos[87] = 523;
        this.noDecimalCos[88] = 348;
        this.noDecimalCos[89] = 174;
        this.noDecimalCos[90] = 0;
        this.noDecimalCos[91] = -174;
        this.noDecimalCos[92] = -348;
        this.noDecimalCos[93] = -523;
        this.noDecimalCos[94] = -697;
        this.noDecimalCos[95] = -871;
        this.noDecimalCos[96] = -1045;
        this.noDecimalCos[97] = -1218;
        this.noDecimalCos[98] = -1391;
        this.noDecimalCos[99] = -1564;
        this.noDecimalCos[100] = -1736;
        this.noDecimalCos[101] = -1908;
        this.noDecimalCos[102] = -2079;
        this.noDecimalCos[103] = -2249;
        this.noDecimalCos[104] = -2419;
        this.noDecimalCos[105] = -2588;
        this.noDecimalCos[106] = -2756;
        this.noDecimalCos[107] = -2923;
        this.noDecimalCos[108] = -3090;
        this.noDecimalCos[109] = -3255;
        this.noDecimalCos[110] = -3420;
        this.noDecimalCos[111] = -3583;
        this.noDecimalCos[112] = -3746;
        this.noDecimalCos[113] = -3907;
        this.noDecimalCos[114] = -4067;
        this.noDecimalCos[115] = -4226;
        this.noDecimalCos[116] = -4383;
        this.noDecimalCos[117] = -4539;
        this.noDecimalCos[118] = -4694;
        this.noDecimalCos[119] = -4848;
        this.noDecimalCos[120] = -4999;
        this.noDecimalCos[121] = -5150;
        this.noDecimalCos[122] = -5299;
        this.noDecimalCos[123] = -5446;
        this.noDecimalCos[124] = -5591;
        this.noDecimalCos[125] = -5735;
        this.noDecimalCos[126] = -5877;
        this.noDecimalCos[127] = -6018;
        this.noDecimalCos[128] = -6156;
        this.noDecimalCos[129] = -6293;
        this.noDecimalCos[130] = -6427;
        this.noDecimalCos[131] = -6560;
        this.noDecimalCos[132] = -6691;
        this.noDecimalCos[133] = -6819;
        this.noDecimalCos[134] = -6946;
        this.noDecimalCos[135] = -7071;
        this.noDecimalCos[136] = -7193;
        this.noDecimalCos[137] = -7313;
        this.noDecimalCos[138] = -7431;
        this.noDecimalCos[139] = -7547;
        this.noDecimalCos[140] = -7660;
        this.noDecimalCos[141] = -7771;
        this.noDecimalCos[142] = -7880;
        this.noDecimalCos[143] = -7986;
        this.noDecimalCos[144] = -8090;
        this.noDecimalCos[145] = -8191;
        this.noDecimalCos[146] = -8290;
        this.noDecimalCos[147] = -8386;
        this.noDecimalCos[148] = -8480;
        this.noDecimalCos[149] = -8571;
        this.noDecimalCos[150] = -8660;
        this.noDecimalCos[151] = -8746;
        this.noDecimalCos[152] = -8829;
        this.noDecimalCos[153] = -8910;
        this.noDecimalCos[154] = -8987;
        this.noDecimalCos[155] = -9063;
        this.noDecimalCos[156] = -9135;
        this.noDecimalCos[157] = -9205;
        this.noDecimalCos[158] = -9271;
        this.noDecimalCos[159] = -9335;
        this.noDecimalCos[160] = -9396;
        this.noDecimalCos[161] = -9455;
        this.noDecimalCos[162] = -9510;
        this.noDecimalCos[163] = -9563;
        this.noDecimalCos[164] = -9612;
        this.noDecimalCos[165] = -9659;
        this.noDecimalCos[166] = -9702;
        this.noDecimalCos[167] = -9743;
        this.noDecimalCos[168] = -9781;
        this.noDecimalCos[169] = -9816;
        this.noDecimalCos[170] = -9848;
        this.noDecimalCos[171] = -9876;
        this.noDecimalCos[172] = -9902;
        this.noDecimalCos[173] = -9925;
        this.noDecimalCos[174] = -9945;
        this.noDecimalCos[175] = -9961;
        this.noDecimalCos[176] = -9975;
        this.noDecimalCos[177] = -9986;
        this.noDecimalCos[178] = -9993;
        this.noDecimalCos[179] = -9998;
        this.noDecimalCos[180] = -10000;
        this.noDecimalCos[181] = -9998;
        this.noDecimalCos[182] = -9993;
        this.noDecimalCos[183] = -9986;
        this.noDecimalCos[184] = -9975;
        this.noDecimalCos[185] = -9961;
        this.noDecimalCos[186] = -9945;
        this.noDecimalCos[187] = -9925;
        this.noDecimalCos[188] = -9902;
        this.noDecimalCos[189] = -9876;
        this.noDecimalCos[190] = -9848;
        this.noDecimalCos[191] = -9816;
        this.noDecimalCos[192] = -9781;
        this.noDecimalCos[193] = -9743;
        this.noDecimalCos[194] = -9702;
        this.noDecimalCos[195] = -9659;
        this.noDecimalCos[196] = -9612;
        this.noDecimalCos[197] = -9563;
        this.noDecimalCos[198] = -9510;
        this.noDecimalCos[199] = -9455;
        this.noDecimalCos[200] = -9396;
        this.noDecimalCos[201] = -9335;
        this.noDecimalCos[202] = -9271;
        this.noDecimalCos[203] = -9205;
        this.noDecimalCos[204] = -9135;
        this.noDecimalCos[205] = -9063;
        this.noDecimalCos[206] = -8987;
        this.noDecimalCos[207] = -8910;
        this.noDecimalCos[208] = -8829;
        this.noDecimalCos[209] = -8746;
        this.noDecimalCos[210] = -8660;
        this.noDecimalCos[211] = -8571;
        this.noDecimalCos[212] = -8480;
        this.noDecimalCos[213] = -8386;
        this.noDecimalCos[214] = -8290;
        this.noDecimalCos[215] = -8191;
        this.noDecimalCos[216] = -8090;
        this.noDecimalCos[217] = -7986;
        this.noDecimalCos[218] = -7880;
        this.noDecimalCos[219] = -7771;
        this.noDecimalCos[220] = -7660;
        this.noDecimalCos[221] = -7547;
        this.noDecimalCos[222] = -7431;
        this.noDecimalCos[223] = -7313;
        this.noDecimalCos[224] = -7193;
        this.noDecimalCos[225] = -7071;
        this.noDecimalCos[226] = -6946;
        this.noDecimalCos[227] = -6819;
        this.noDecimalCos[228] = -6691;
        this.noDecimalCos[229] = -6560;
        this.noDecimalCos[230] = -6427;
        this.noDecimalCos[231] = -6293;
        this.noDecimalCos[232] = -6156;
        this.noDecimalCos[233] = -6018;
        this.noDecimalCos[234] = -5877;
        this.noDecimalCos[235] = -5735;
        this.noDecimalCos[236] = -5591;
        this.noDecimalCos[237] = -5446;
        this.noDecimalCos[238] = -5299;
        this.noDecimalCos[239] = -5150;
        this.noDecimalCos[240] = -5000;
        this.noDecimalCos[241] = -4848;
        this.noDecimalCos[242] = -4694;
        this.noDecimalCos[243] = -4539;
        this.noDecimalCos[244] = -4383;
        this.noDecimalCos[245] = -4226;
        this.noDecimalCos[246] = -4067;
        this.noDecimalCos[247] = -3907;
        this.noDecimalCos[248] = -3746;
        this.noDecimalCos[249] = -3583;
        this.noDecimalCos[250] = -3420;
        this.noDecimalCos[251] = -3255;
        this.noDecimalCos[252] = -3090;
        this.noDecimalCos[253] = -2923;
        this.noDecimalCos[254] = -2756;
        this.noDecimalCos[255] = -2588;
        this.noDecimalCos[256] = -2419;
        this.noDecimalCos[257] = -2249;
        this.noDecimalCos[258] = -2079;
        this.noDecimalCos[259] = -1908;
        this.noDecimalCos[260] = -1736;
        this.noDecimalCos[261] = -1564;
        this.noDecimalCos[262] = -1391;
        this.noDecimalCos[263] = -1218;
        this.noDecimalCos[264] = -1045;
        this.noDecimalCos[265] = -871;
        this.noDecimalCos[266] = -697;
        this.noDecimalCos[267] = -523;
        this.noDecimalCos[268] = -348;
        this.noDecimalCos[269] = -174;
        this.noDecimalCos[270] = 0;
        this.noDecimalCos[271] = 174;
        this.noDecimalCos[272] = 348;
        this.noDecimalCos[273] = 523;
        this.noDecimalCos[274] = 697;
        this.noDecimalCos[275] = 871;
        this.noDecimalCos[276] = 1045;
        this.noDecimalCos[277] = 1218;
        this.noDecimalCos[278] = 1391;
        this.noDecimalCos[279] = 1564;
        this.noDecimalCos[280] = 1736;
        this.noDecimalCos[281] = 1908;
        this.noDecimalCos[282] = 2079;
        this.noDecimalCos[283] = 2249;
        this.noDecimalCos[284] = 2419;
        this.noDecimalCos[285] = 2588;
        this.noDecimalCos[286] = 2756;
        this.noDecimalCos[287] = 2923;
        this.noDecimalCos[288] = 3090;
        this.noDecimalCos[289] = 3255;
        this.noDecimalCos[290] = 3420;
        this.noDecimalCos[291] = 3583;
        this.noDecimalCos[292] = 3746;
        this.noDecimalCos[293] = 3907;
        this.noDecimalCos[294] = 4067;
        this.noDecimalCos[295] = 4226;
        this.noDecimalCos[296] = 4383;
        this.noDecimalCos[297] = 4539;
        this.noDecimalCos[298] = 4694;
        this.noDecimalCos[299] = 4848;
        this.noDecimalCos[300] = 5000;
        this.noDecimalCos[301] = 5150;
        this.noDecimalCos[302] = 5299;
        this.noDecimalCos[303] = 5446;
        this.noDecimalCos[304] = 5591;
        this.noDecimalCos[305] = 5735;
        this.noDecimalCos[306] = 5877;
        this.noDecimalCos[307] = 6018;
        this.noDecimalCos[308] = 6156;
        this.noDecimalCos[309] = 6293;
        this.noDecimalCos[310] = 6427;
        this.noDecimalCos[311] = 6560;
        this.noDecimalCos[312] = 6691;
        this.noDecimalCos[313] = 6819;
        this.noDecimalCos[314] = 6946;
        this.noDecimalCos[315] = 7071;
        this.noDecimalCos[316] = 7193;
        this.noDecimalCos[317] = 7313;
        this.noDecimalCos[318] = 7431;
        this.noDecimalCos[319] = 7547;
        this.noDecimalCos[320] = 7660;
        this.noDecimalCos[321] = 7771;
        this.noDecimalCos[322] = 7880;
        this.noDecimalCos[323] = 7986;
        this.noDecimalCos[324] = 8090;
        this.noDecimalCos[325] = 8191;
        this.noDecimalCos[326] = 8290;
        this.noDecimalCos[327] = 8386;
        this.noDecimalCos[328] = 8480;
        this.noDecimalCos[329] = 8571;
        this.noDecimalCos[330] = 8660;
        this.noDecimalCos[331] = 8746;
        this.noDecimalCos[332] = 8829;
        this.noDecimalCos[333] = 8910;
        this.noDecimalCos[334] = 8987;
        this.noDecimalCos[335] = 9063;
        this.noDecimalCos[336] = 9135;
        this.noDecimalCos[337] = 9205;
        this.noDecimalCos[338] = 9271;
        this.noDecimalCos[339] = 9335;
        this.noDecimalCos[340] = 9396;
        this.noDecimalCos[341] = 9455;
        this.noDecimalCos[342] = 9510;
        this.noDecimalCos[343] = 9563;
        this.noDecimalCos[344] = 9612;
        this.noDecimalCos[345] = 9659;
        this.noDecimalCos[346] = 9702;
        this.noDecimalCos[347] = 9743;
        this.noDecimalCos[348] = 9781;
        this.noDecimalCos[349] = 9816;
        this.noDecimalCos[350] = 9848;
        this.noDecimalCos[351] = 9876;
        this.noDecimalCos[352] = 9902;
        this.noDecimalCos[353] = 9925;
        this.noDecimalCos[354] = 9945;
        this.noDecimalCos[355] = 9961;
        this.noDecimalCos[356] = 9975;
        this.noDecimalCos[357] = 9986;
        this.noDecimalCos[358] = 9993;
        this.noDecimalCos[359] = 9998;

        this.noDecimalTan[0] = 0;
        this.noDecimalTan[1] = 174;
        this.noDecimalTan[2] = 349;
        this.noDecimalTan[3] = 524;
        this.noDecimalTan[4] = 699;
        this.noDecimalTan[5] = 874;
        this.noDecimalTan[6] = 1051;
        this.noDecimalTan[7] = 1227;
        this.noDecimalTan[8] = 1405;
        this.noDecimalTan[9] = 1583;
        this.noDecimalTan[10] = 1763;
        this.noDecimalTan[11] = 1943;
        this.noDecimalTan[12] = 2125;
        this.noDecimalTan[13] = 2308;
        this.noDecimalTan[14] = 2493;
        this.noDecimalTan[15] = 2679;
        this.noDecimalTan[16] = 2867;
        this.noDecimalTan[17] = 3057;
        this.noDecimalTan[18] = 3249;
        this.noDecimalTan[19] = 3443;
        this.noDecimalTan[20] = 3639;
        this.noDecimalTan[21] = 3838;
        this.noDecimalTan[22] = 4040;
        this.noDecimalTan[23] = 4244;
        this.noDecimalTan[24] = 4452;
        this.noDecimalTan[25] = 4663;
        this.noDecimalTan[26] = 4877;
        this.noDecimalTan[27] = 5095;
        this.noDecimalTan[28] = 5317;
        this.noDecimalTan[29] = 5543;
        this.noDecimalTan[30] = 5773;
        this.noDecimalTan[31] = 6008;
        this.noDecimalTan[32] = 6248;
        this.noDecimalTan[33] = 6494;
        this.noDecimalTan[34] = 6745;
        this.noDecimalTan[35] = 7002;
        this.noDecimalTan[36] = 7265;
        this.noDecimalTan[37] = 7535;
        this.noDecimalTan[38] = 7812;
        this.noDecimalTan[39] = 8097;
        this.noDecimalTan[40] = 8390;
        this.noDecimalTan[41] = 8692;
        this.noDecimalTan[42] = 9004;
        this.noDecimalTan[43] = 9325;
        this.noDecimalTan[44] = 9656;
        this.noDecimalTan[45] = 9999;

        this.noDecimalTan[46] = 10355;
        this.noDecimalTan[47] = 10723;
        this.noDecimalTan[48] = 11106;
        this.noDecimalTan[49] = 11503;
        this.noDecimalTan[50] = 11917;
        this.noDecimalTan[51] = 12348;
        this.noDecimalTan[52] = 12799;
        this.noDecimalTan[53] = 13270;
        this.noDecimalTan[54] = 13763;
        this.noDecimalTan[55] = 14281;
        this.noDecimalTan[56] = 14825;
        this.noDecimalTan[57] = 15398;
        this.noDecimalTan[58] = 16003;
        this.noDecimalTan[59] = 16642;
        this.noDecimalTan[60] = 17320;
        this.noDecimalTan[61] = 18040;
        this.noDecimalTan[62] = 18807;
        this.noDecimalTan[63] = 19626;
        this.noDecimalTan[64] = 20503;
        this.noDecimalTan[65] = 21445;
        this.noDecimalTan[66] = 22460;
        this.noDecimalTan[67] = 23558;
        this.noDecimalTan[68] = 24750;
        this.noDecimalTan[69] = 26050;
        this.noDecimalTan[70] = 27474;
        this.noDecimalTan[71] = 29042;
        this.noDecimalTan[72] = 30776;
        this.noDecimalTan[73] = 32708;
        this.noDecimalTan[74] = 34874;
        this.noDecimalTan[75] = 37320;
        this.noDecimalTan[76] = 40107;
        this.noDecimalTan[77] = 43314;
        this.noDecimalTan[78] = 47046;
        this.noDecimalTan[79] = 51445;
        this.noDecimalTan[80] = 56712;
        this.noDecimalTan[81] = 63137;
        this.noDecimalTan[82] = 71153;
        this.noDecimalTan[83] = 81443;
        this.noDecimalTan[84] = 95143;
        this.noDecimalTan[85] = 114300;
        this.noDecimalTan[86] = 143006;
        this.noDecimalTan[87] = 190811;
        this.noDecimalTan[88] = 286362;
        this.noDecimalTan[89] = 572899;
        this.noDecimalTan[90] = MAX_VALUE;
        this.noDecimalTan[91] = -572899;
        this.noDecimalTan[92] = -286362;
        this.noDecimalTan[93] = -190811;
        this.noDecimalTan[94] = -143006;
        this.noDecimalTan[95] = -114300;
        this.noDecimalTan[96] = -95143;
        this.noDecimalTan[97] = -81443;
        this.noDecimalTan[98] = -71153;
        this.noDecimalTan[99] = -63137;
        this.noDecimalTan[100] = -56712;
        this.noDecimalTan[101] = -51445;
        this.noDecimalTan[102] = -47046;
        this.noDecimalTan[103] = -43314;
        this.noDecimalTan[104] = -40107;
        this.noDecimalTan[105] = -37320;
        this.noDecimalTan[106] = -34874;
        this.noDecimalTan[107] = -32708;
        this.noDecimalTan[108] = -30776;
        this.noDecimalTan[109] = -29042;
        this.noDecimalTan[110] = -27474;
        this.noDecimalTan[111] = -26050;
        this.noDecimalTan[112] = -24750;
        this.noDecimalTan[113] = -23558;
        this.noDecimalTan[114] = -22460;
        this.noDecimalTan[115] = -21445;
        this.noDecimalTan[116] = -20503;
        this.noDecimalTan[117] = -19626;
        this.noDecimalTan[118] = -18807;
        this.noDecimalTan[119] = -18040;
        this.noDecimalTan[120] = -17320;
        this.noDecimalTan[121] = -16642;
        this.noDecimalTan[122] = -16003;
        this.noDecimalTan[123] = -15398;
        this.noDecimalTan[124] = -14825;
        this.noDecimalTan[125] = -14281;
        this.noDecimalTan[126] = -13763;
        this.noDecimalTan[127] = -13270;
        this.noDecimalTan[128] = -12799;
        this.noDecimalTan[129] = -12348;
        this.noDecimalTan[130] = -11917;
        this.noDecimalTan[131] = -11503;
        this.noDecimalTan[132] = -11106;
        this.noDecimalTan[133] = -10723;
        this.noDecimalTan[134] = -10355;

        this.noDecimalTan[135] = -10000;
        this.noDecimalTan[136] = -9656;
        this.noDecimalTan[137] = -9325;
        this.noDecimalTan[138] = -9004;
        this.noDecimalTan[139] = -8692;
        this.noDecimalTan[140] = -8390;
        this.noDecimalTan[141] = -8097;
        this.noDecimalTan[142] = -7812;
        this.noDecimalTan[143] = -7535;
        this.noDecimalTan[144] = -7265;
        this.noDecimalTan[145] = -7002;
        this.noDecimalTan[146] = -6745;
        this.noDecimalTan[147] = -6494;
        this.noDecimalTan[148] = -6248;
        this.noDecimalTan[149] = -6008;
        this.noDecimalTan[150] = -5773;
        this.noDecimalTan[151] = -5543;
        this.noDecimalTan[152] = -5317;
        this.noDecimalTan[153] = -5095;
        this.noDecimalTan[154] = -4877;
        this.noDecimalTan[155] = -4663;
        this.noDecimalTan[156] = -4452;
        this.noDecimalTan[157] = -4244;
        this.noDecimalTan[158] = -4040;
        this.noDecimalTan[159] = -3838;
        this.noDecimalTan[160] = -3639;
        this.noDecimalTan[161] = -3443;
        this.noDecimalTan[162] = -3249;
        this.noDecimalTan[163] = -3057;
        this.noDecimalTan[164] = -2867;
        this.noDecimalTan[165] = -2679;
        this.noDecimalTan[166] = -2493;
        this.noDecimalTan[167] = -2308;
        this.noDecimalTan[168] = -2125;
        this.noDecimalTan[169] = -1943;
        this.noDecimalTan[170] = -1763;
        this.noDecimalTan[171] = -1583;
        this.noDecimalTan[172] = -1405;
        this.noDecimalTan[173] = -1227;
        this.noDecimalTan[174] = -1051;
        this.noDecimalTan[175] = -874;
        this.noDecimalTan[176] = -699;
        this.noDecimalTan[177] = -524;
        this.noDecimalTan[178] = -349;
        this.noDecimalTan[179] = -174;
        this.noDecimalTan[180] = 0;
        this.noDecimalTan[181] = 174;
        this.noDecimalTan[182] = 349;
        this.noDecimalTan[183] = 524;
        this.noDecimalTan[184] = 699;
        this.noDecimalTan[185] = 874;
        this.noDecimalTan[186] = 1051;
        this.noDecimalTan[187] = 1227;
        this.noDecimalTan[188] = 1405;
        this.noDecimalTan[189] = 1583;
        this.noDecimalTan[190] = 1763;
        this.noDecimalTan[191] = 1943;
        this.noDecimalTan[192] = 2125;
        this.noDecimalTan[193] = 2308;
        this.noDecimalTan[194] = 2493;
        this.noDecimalTan[195] = 2679;
        this.noDecimalTan[196] = 2867;
        this.noDecimalTan[197] = 3057;
        this.noDecimalTan[198] = 3249;
        this.noDecimalTan[199] = 3443;
        this.noDecimalTan[200] = 3639;
        this.noDecimalTan[201] = 3838;
        this.noDecimalTan[202] = 4040;
        this.noDecimalTan[203] = 4244;
        this.noDecimalTan[204] = 4452;
        this.noDecimalTan[205] = 4663;
        this.noDecimalTan[206] = 4877;
        this.noDecimalTan[207] = 5095;
        this.noDecimalTan[208] = 5317;
        this.noDecimalTan[209] = 5543;
        this.noDecimalTan[210] = 5773;
        this.noDecimalTan[211] = 6008;
        this.noDecimalTan[212] = 6248;
        this.noDecimalTan[213] = 6494;
        this.noDecimalTan[214] = 6745;
        this.noDecimalTan[215] = 7002;
        this.noDecimalTan[216] = 7265;
        this.noDecimalTan[217] = 7535;
        this.noDecimalTan[218] = 7812;
        this.noDecimalTan[219] = 8097;
        this.noDecimalTan[220] = 8390;
        this.noDecimalTan[221] = 8692;
        this.noDecimalTan[222] = 9004;
        this.noDecimalTan[223] = 9325;
        this.noDecimalTan[224] = 9656;
        this.noDecimalTan[225] = 9999;

        this.noDecimalTan[226] = 10355;
        this.noDecimalTan[227] = 10723;
        this.noDecimalTan[228] = 11106;
        this.noDecimalTan[229] = 11503;
        this.noDecimalTan[230] = 11917;
        this.noDecimalTan[231] = 12348;
        this.noDecimalTan[232] = 12799;
        this.noDecimalTan[233] = 13270;
        this.noDecimalTan[234] = 13763;
        this.noDecimalTan[235] = 14281;
        this.noDecimalTan[236] = 14825;
        this.noDecimalTan[237] = 15398;
        this.noDecimalTan[238] = 16003;
        this.noDecimalTan[239] = 16642;
        this.noDecimalTan[240] = 17320;
        this.noDecimalTan[241] = 18040;
        this.noDecimalTan[242] = 18807;
        this.noDecimalTan[243] = 19626;
        this.noDecimalTan[244] = 20503;
        this.noDecimalTan[245] = 21445;
        this.noDecimalTan[246] = 22460;
        this.noDecimalTan[247] = 23558;
        this.noDecimalTan[248] = 24750;
        this.noDecimalTan[249] = 26050;
        this.noDecimalTan[250] = 27474;
        this.noDecimalTan[251] = 29042;
        this.noDecimalTan[252] = 30776;
        this.noDecimalTan[253] = 32708;
        this.noDecimalTan[254] = 34874;
        this.noDecimalTan[255] = 37320;
        this.noDecimalTan[256] = 40107;
        this.noDecimalTan[257] = 43314;
        this.noDecimalTan[258] = 47046;
        this.noDecimalTan[259] = 51445;
        this.noDecimalTan[260] = 56712;
        this.noDecimalTan[261] = 63137;
        this.noDecimalTan[262] = 71153;
        this.noDecimalTan[263] = 81443;
        this.noDecimalTan[264] = 95143;
        this.noDecimalTan[265] = 114300;
        this.noDecimalTan[266] = 143006;
        this.noDecimalTan[267] = 190811;
        this.noDecimalTan[268] = 286362;
        this.noDecimalTan[269] = 572899;
        this.noDecimalTan[270] = MAX_VALUE;
        this.noDecimalTan[271] = -572899;
        this.noDecimalTan[272] = -286362;
        this.noDecimalTan[273] = -190811;
        this.noDecimalTan[274] = -143006;
        this.noDecimalTan[275] = -114300;
        this.noDecimalTan[276] = -95143;
        this.noDecimalTan[277] = -81443;
        this.noDecimalTan[278] = -71153;
        this.noDecimalTan[279] = -63137;
        this.noDecimalTan[280] = -56712;
        this.noDecimalTan[281] = -51445;
        this.noDecimalTan[282] = -47046;
        this.noDecimalTan[283] = -43314;
        this.noDecimalTan[284] = -40107;
        this.noDecimalTan[285] = -37320;
        this.noDecimalTan[286] = -34874;
        this.noDecimalTan[287] = -32708;
        this.noDecimalTan[288] = -30776;
        this.noDecimalTan[289] = -29042;
        this.noDecimalTan[290] = -27474;
        this.noDecimalTan[291] = -26050;
        this.noDecimalTan[292] = -24750;
        this.noDecimalTan[293] = -23558;
        this.noDecimalTan[294] = -22460;
        this.noDecimalTan[295] = -21445;
        this.noDecimalTan[296] = -20503;
        this.noDecimalTan[297] = -19626;
        this.noDecimalTan[298] = -18807;
        this.noDecimalTan[299] = -18040;
        this.noDecimalTan[300] = -17320;
        this.noDecimalTan[301] = -16642;
        this.noDecimalTan[302] = -16003;
        this.noDecimalTan[303] = -15398;
        this.noDecimalTan[304] = -14825;
        this.noDecimalTan[305] = -14281;
        this.noDecimalTan[306] = -13763;
        this.noDecimalTan[307] = -13270;
        this.noDecimalTan[308] = -12799;
        this.noDecimalTan[309] = -12348;
        this.noDecimalTan[310] = -11917;
        this.noDecimalTan[311] = -11503;
        this.noDecimalTan[312] = -11106;
        this.noDecimalTan[313] = -10723;
        this.noDecimalTan[314] = -10355;

        this.noDecimalTan[315] = -10000;
        this.noDecimalTan[316] = -9656;
        this.noDecimalTan[317] = -9325;
        this.noDecimalTan[318] = -9004;
        this.noDecimalTan[319] = -8692;
        this.noDecimalTan[320] = -8390;
        this.noDecimalTan[321] = -8097;
        this.noDecimalTan[322] = -7812;
        this.noDecimalTan[323] = -7535;
        this.noDecimalTan[324] = -7265;
        this.noDecimalTan[325] = -7002;
        this.noDecimalTan[326] = -6745;
        this.noDecimalTan[327] = -6494;
        this.noDecimalTan[328] = -6248;
        this.noDecimalTan[329] = -6008;
        this.noDecimalTan[330] = -5773;
        this.noDecimalTan[331] = -5543;
        this.noDecimalTan[332] = -5317;
        this.noDecimalTan[333] = -5095;
        this.noDecimalTan[334] = -4877;
        this.noDecimalTan[335] = -4663;
        this.noDecimalTan[336] = -4452;
        this.noDecimalTan[337] = -4244;
        this.noDecimalTan[338] = -4040;
        this.noDecimalTan[339] = -3838;
        this.noDecimalTan[340] = -3639;
        this.noDecimalTan[341] = -3443;
        this.noDecimalTan[342] = -3249;
        this.noDecimalTan[343] = -3057;
        this.noDecimalTan[344] = -2867;
        this.noDecimalTan[345] = -2679;
        this.noDecimalTan[346] = -2493;
        this.noDecimalTan[347] = -2308;
        this.noDecimalTan[348] = -2125;
        this.noDecimalTan[349] = -1943;
        this.noDecimalTan[350] = -1763;
        this.noDecimalTan[351] = -1583;
        this.noDecimalTan[352] = -1405;
        this.noDecimalTan[353] = -1227;
        this.noDecimalTan[354] = -1051;
        this.noDecimalTan[355] = -874;
        this.noDecimalTan[356] = -699;
        this.noDecimalTan[357] = -524;
        this.noDecimalTan[358] = -349;
        this.noDecimalTan[359] = -174;
        this.noDecimalTan[0] = -174;
    }

    public long sin(int angle)
    {
        return this.noDecimalSin[angle];
    }

    public long cos(int angle)
    {
        return this.noDecimalCos[angle];
    }

    public long tan(int angle)
    {
        return this.noDecimalTan[angle];
    }
    
    public int SCALE = 10000;
    
    //private final String ANTITAN = "antiTan";
    
    public short antiTan(int dx, int dy)
       throws Exception
    {
        final long MAX_VALUE = ((long) Integer.MAX_VALUE);
        final long MIN_VALUE = ((long) Integer.MIN_VALUE);
        long ratio = (long) (MAX_VALUE - 1);
        if (dy != 0)
        {
            long dxl = ((long) dx);
            ratio = this.SCALE * dxl / dy;

            //Major bug in Android?
            if(dx <= 0 && dy < 0)
            {
            	//PreLogUtil.put("How is dx or dy not negative? " + PositionStrings + dx + PositionStrings + dy, this, ANTITAN);
            	ratio = this.mathUtil.abslong(ratio);
            }

            //this.logUtil.putF("ratioUnscaled: " + ratioUnscaled, this, ANTITAN);
        }

        if ((dx >= 0 && dy < 0))
        {
            for (int index = 180; index > 90; index--)
            {
                if (ratio <= this.noDecimalTan[index])
                {
                    if(ratio > this.noDecimalTan[index - 1])
                    {
                        //this.logUtil.putF(Integer.toString(result), this, ANTITAN));
                        return ((short) index);
                    }
                    else
                        if(this.noDecimalTan[index - 1] == MAX_VALUE && ratio > MIN_VALUE)
                        {
                            return ((short) index);
                        }
                }
            }
        }
        else
        if ((dx < 0 && dy <= 0))
        {
            for (int index = 270; index > 180; index--)
            {
                if (ratio < this.noDecimalTan[index] && ratio >= this.noDecimalTan[index - 1])
                {
                    //this.logUtil.putF(Integer.toString(result), this, ANTITAN));
                    return ((short) index);
                }
            }
        }
        else
        if ((dx < 0 && dy > 0))
        {
            for (int index = (this.noDecimalTan.length - 1); index > 270; index--)
            {
                if (ratio < this.noDecimalTan[index])
                {
                    if(ratio >= this.noDecimalTan[index - 1])
                    {
                        //this.logUtil.putF(Integer.toString(result), this, ANTITAN));
                        return ((short) index);
                    }
                    else
                        if(this.noDecimalTan[index - 1] == MAX_VALUE && ratio >= MIN_VALUE)
                        {
                            return ((short) index);
                        }
                }
            }
        }
        else
        if ((dx >= 0 && dy >= 0))
        {
            for (int index = 90; index > 0; index--)
            {
                if (ratio < this.noDecimalTan[index] && ratio >= this.noDecimalTan[index - 1])
                {
                    //this.logUtil.putF(Integer.toString(result), this, ANTITAN);
                    return ((short) index);
                }
            }
        }

        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Invalid Ratio: ");
        stringBuffer.appendlong(ratio);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(PositionStrings.getInstance().DX_LABEL);
        stringBuffer.appendint(dx);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(PositionStrings.getInstance().DX_LABEL);
        stringBuffer.appendint(dy);
        
        throw new Exception(stringBuffer.toString());
    }
    
    //Used to help figure out the correct quadrants when +- values are mixed up
    public short antiTanDebug(int screenX, int screenY, int targetX, int targetY)
    throws Exception
    {
		final String METHOD_NAME = "antiTanDebug";
		final String TARGET_ANGLE = " Targeting angle: ";

                final StringMaker stringMaker = new StringMaker();
                
		PreLogUtil.put(stringMaker.append("screenX: ").appendint(screenX).append(" screenY: ").appendint(screenY).append(" targetX: ").appendint(targetX).append(" targetY: ").appendint(targetY).toString(), this, METHOD_NAME);
		
		int targetX2 = (int) -targetX;
		int targetY2 = (int) -targetY;

		int screenX2 = (int) -screenX;
		int screenY2 = (int) -screenY;

		int[] dx = new int[4];
		dx[0] = (screenX - targetX);
		dx[1] = (screenX - targetX2);
		dx[2] = (screenX2 - targetX);
		dx[3] = (screenX2 - targetX2);

		int[] dy = new int[4];
		dy[0] = (screenY - targetY);
		dy[1] = (screenY - targetY2);
		dy[2] = (screenY2 - targetY);
		dy[3] = (screenY2 - targetY2);

		short angleOfTarget;

                final AngleFactory angleFactory = AngleFactory.getInstance();
                Angle angle;
		for(int index = 0; index < dx.length; index++)
		{
			for(int index2 = 0; index2 < dy.length; index2++)
			{
				angleOfTarget = this.antiTan(dx[index], dy[index2]);
				angle = angleFactory.getAt((int) angleOfTarget);
                                stringMaker.delete(0, stringMaker.length());
				PreLogUtil.put(stringMaker.append(CommonLabels.getInstance().INDEX_LABEL).appendint(index).append(" index2: ").appendint(index2).append(PositionStrings.getInstance().DX_LABEL).appendint(dx[index]).append(PositionStrings.getInstance().DY_LABEL).appendint(dy[index2]).append(TARGET_ANGLE).appendshort(angleOfTarget).append(CommonSeps.getInstance().EQUALS).appendshort(angle.getValue()).toString(), this, METHOD_NAME);
			}
		}

		angleOfTarget = this.antiTan(dx[0], dy[0]);

		return angleOfTarget;
    }
}
