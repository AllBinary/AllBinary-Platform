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
        return instance;
    }

    private final MathUtil mathUtil = MathUtil.getInstance();
    
    private final long[] noDecimalSin;
    private final long[] noDecimalCos;
    private final long[] noDecimalTan;

    private NoDecimalTrigTable()
    {
        final int TOTAL_ANGLE = AngleFactory.getInstance().TOTAL_ANGLE;
        
        noDecimalSin = new long[TOTAL_ANGLE];
        noDecimalCos = new long[TOTAL_ANGLE];
        noDecimalTan = new long[TOTAL_ANGLE + 1];

        noDecimalSin[0] = 0;
        noDecimalSin[1] = 174;
        noDecimalSin[2] = 348;
        noDecimalSin[3] = 523;
        noDecimalSin[4] = 697;
        noDecimalSin[5] = 871;
        noDecimalSin[6] = 1045;
        noDecimalSin[7] = 1218;
        noDecimalSin[8] = 1391;
        noDecimalSin[9] = 1564;
        noDecimalSin[10] = 1736;
        noDecimalSin[11] = 1908;
        noDecimalSin[12] = 2079;
        noDecimalSin[13] = 2249;
        noDecimalSin[14] = 2419;
        noDecimalSin[15] = 2588;
        noDecimalSin[16] = 2756;
        noDecimalSin[17] = 2923;
        noDecimalSin[18] = 3090;
        noDecimalSin[19] = 3255;
        noDecimalSin[20] = 3420;
        noDecimalSin[21] = 3583;
        noDecimalSin[22] = 3746;
        noDecimalSin[23] = 3907;
        noDecimalSin[24] = 4067;
        noDecimalSin[25] = 4226;
        noDecimalSin[26] = 4383;
        noDecimalSin[27] = 4539;
        noDecimalSin[28] = 4694;
        noDecimalSin[29] = 4848;
        noDecimalSin[30] = 4999;
        noDecimalSin[31] = 5150;
        noDecimalSin[32] = 5299;
        noDecimalSin[33] = 5446;
        noDecimalSin[34] = 5591;
        noDecimalSin[35] = 5735;
        noDecimalSin[36] = 5877;
        noDecimalSin[37] = 6018;
        noDecimalSin[38] = 6156;
        noDecimalSin[39] = 6293;
        noDecimalSin[40] = 6427;
        noDecimalSin[41] = 6560;
        noDecimalSin[42] = 6691;
        noDecimalSin[43] = 6819;
        noDecimalSin[44] = 6946;
        noDecimalSin[45] = 7071;
        noDecimalSin[46] = 7193;
        noDecimalSin[47] = 7313;
        noDecimalSin[48] = 7431;
        noDecimalSin[49] = 7547;
        noDecimalSin[50] = 7660;
        noDecimalSin[51] = 7771;
        noDecimalSin[52] = 7880;
        noDecimalSin[53] = 7986;
        noDecimalSin[54] = 8090;
        noDecimalSin[55] = 8191;
        noDecimalSin[56] = 8290;
        noDecimalSin[57] = 8386;
        noDecimalSin[58] = 8480;
        noDecimalSin[59] = 8571;
        noDecimalSin[60] = 8660;
        noDecimalSin[61] = 8746;
        noDecimalSin[62] = 8829;
        noDecimalSin[63] = 8910;
        noDecimalSin[64] = 8987;
        noDecimalSin[65] = 9063;
        noDecimalSin[66] = 9135;
        noDecimalSin[67] = 9205;
        noDecimalSin[68] = 9271;
        noDecimalSin[69] = 9335;
        noDecimalSin[70] = 9396;
        noDecimalSin[71] = 9455;
        noDecimalSin[72] = 9510;
        noDecimalSin[73] = 9563;
        noDecimalSin[74] = 9612;
        noDecimalSin[75] = 9659;
        noDecimalSin[76] = 9702;
        noDecimalSin[77] = 9743;
        noDecimalSin[78] = 9781;
        noDecimalSin[79] = 9816;
        noDecimalSin[80] = 9848;
        noDecimalSin[81] = 9876;
        noDecimalSin[82] = 9902;
        noDecimalSin[83] = 9925;
        noDecimalSin[84] = 9945;
        noDecimalSin[85] = 9961;
        noDecimalSin[86] = 9975;
        noDecimalSin[87] = 9986;
        noDecimalSin[88] = 9993;
        noDecimalSin[89] = 9998;
        noDecimalSin[90] = 10000;
        noDecimalSin[91] = 9998;
        noDecimalSin[92] = 9993;
        noDecimalSin[93] = 9986;
        noDecimalSin[94] = 9975;
        noDecimalSin[95] = 9961;
        noDecimalSin[96] = 9945;
        noDecimalSin[97] = 9925;
        noDecimalSin[98] = 9902;
        noDecimalSin[99] = 9876;
        noDecimalSin[100] = 9848;
        noDecimalSin[101] = 9816;
        noDecimalSin[102] = 9781;
        noDecimalSin[103] = 9743;
        noDecimalSin[104] = 9702;
        noDecimalSin[105] = 9659;
        noDecimalSin[106] = 9612;
        noDecimalSin[107] = 9563;
        noDecimalSin[108] = 9510;
        noDecimalSin[109] = 9455;
        noDecimalSin[110] = 9396;
        noDecimalSin[111] = 9335;
        noDecimalSin[112] = 9271;
        noDecimalSin[113] = 9205;
        noDecimalSin[114] = 9135;
        noDecimalSin[115] = 9063;
        noDecimalSin[116] = 8987;
        noDecimalSin[117] = 8910;
        noDecimalSin[118] = 8829;
        noDecimalSin[119] = 8746;
        noDecimalSin[120] = 8660;
        noDecimalSin[121] = 8571;
        noDecimalSin[122] = 8480;
        noDecimalSin[123] = 8386;
        noDecimalSin[124] = 8290;
        noDecimalSin[125] = 8191;
        noDecimalSin[126] = 8090;
        noDecimalSin[127] = 7986;
        noDecimalSin[128] = 7880;
        noDecimalSin[129] = 7771;
        noDecimalSin[130] = 7660;
        noDecimalSin[131] = 7547;
        noDecimalSin[132] = 7431;
        noDecimalSin[133] = 7313;
        noDecimalSin[134] = 7193;
        noDecimalSin[135] = 7071;
        noDecimalSin[136] = 6946;
        noDecimalSin[137] = 6819;
        noDecimalSin[138] = 6691;
        noDecimalSin[139] = 6560;
        noDecimalSin[140] = 6427;
        noDecimalSin[141] = 6293;
        noDecimalSin[142] = 6156;
        noDecimalSin[143] = 6018;
        noDecimalSin[144] = 5877;
        noDecimalSin[145] = 5735;
        noDecimalSin[146] = 5591;
        noDecimalSin[147] = 5446;
        noDecimalSin[148] = 5299;
        noDecimalSin[149] = 5150;
        noDecimalSin[150] = 4999;
        noDecimalSin[151] = 4848;
        noDecimalSin[152] = 4694;
        noDecimalSin[153] = 4539;
        noDecimalSin[154] = 4383;
        noDecimalSin[155] = 4226;
        noDecimalSin[156] = 4067;
        noDecimalSin[157] = 3907;
        noDecimalSin[158] = 3746;
        noDecimalSin[159] = 3583;
        noDecimalSin[160] = 3420;
        noDecimalSin[161] = 3255;
        noDecimalSin[162] = 3090;
        noDecimalSin[163] = 2923;
        noDecimalSin[164] = 2756;
        noDecimalSin[165] = 2588;
        noDecimalSin[166] = 2419;
        noDecimalSin[167] = 2249;
        noDecimalSin[168] = 2079;
        noDecimalSin[169] = 1908;
        noDecimalSin[170] = 1736;
        noDecimalSin[171] = 1564;
        noDecimalSin[172] = 1391;
        noDecimalSin[173] = 1218;
        noDecimalSin[174] = 1045;
        noDecimalSin[175] = 871;
        noDecimalSin[176] = 697;
        noDecimalSin[177] = 523;
        noDecimalSin[178] = 348;
        noDecimalSin[179] = 174;
        noDecimalSin[180] = 0;
        noDecimalSin[181] = -174;
        noDecimalSin[182] = -348;
        noDecimalSin[183] = -523;
        noDecimalSin[184] = -697;
        noDecimalSin[185] = -871;
        noDecimalSin[186] = -1045;
        noDecimalSin[187] = -1218;
        noDecimalSin[188] = -1391;
        noDecimalSin[189] = -1564;
        noDecimalSin[190] = -1736;
        noDecimalSin[191] = -1908;
        noDecimalSin[192] = -2079;
        noDecimalSin[193] = -2249;
        noDecimalSin[194] = -2419;
        noDecimalSin[195] = -2588;
        noDecimalSin[196] = -2756;
        noDecimalSin[197] = -2923;
        noDecimalSin[198] = -3090;
        noDecimalSin[199] = -3255;
        noDecimalSin[200] = -3420;
        noDecimalSin[201] = -3583;
        noDecimalSin[202] = -3746;
        noDecimalSin[203] = -3907;
        noDecimalSin[204] = -4067;
        noDecimalSin[205] = -4226;
        noDecimalSin[206] = -4383;
        noDecimalSin[207] = -4539;
        noDecimalSin[208] = -4694;
        noDecimalSin[209] = -4848;
        noDecimalSin[210] = -5000;
        noDecimalSin[211] = -5150;
        noDecimalSin[212] = -5299;
        noDecimalSin[213] = -5446;
        noDecimalSin[214] = -5591;
        noDecimalSin[215] = -5735;
        noDecimalSin[216] = -5877;
        noDecimalSin[217] = -6018;
        noDecimalSin[218] = -6156;
        noDecimalSin[219] = -6293;
        noDecimalSin[220] = -6427;
        noDecimalSin[221] = -6560;
        noDecimalSin[222] = -6691;
        noDecimalSin[223] = -6819;
        noDecimalSin[224] = -6946;
        noDecimalSin[225] = -7071;
        noDecimalSin[226] = -7193;
        noDecimalSin[227] = -7313;
        noDecimalSin[228] = -7431;
        noDecimalSin[229] = -7547;
        noDecimalSin[230] = -7660;
        noDecimalSin[231] = -7771;
        noDecimalSin[232] = -7880;
        noDecimalSin[233] = -7986;
        noDecimalSin[234] = -8090;
        noDecimalSin[235] = -8191;
        noDecimalSin[236] = -8290;
        noDecimalSin[237] = -8386;
        noDecimalSin[238] = -8480;
        noDecimalSin[239] = -8571;
        noDecimalSin[240] = -8660;
        noDecimalSin[241] = -8746;
        noDecimalSin[242] = -8829;
        noDecimalSin[243] = -8910;
        noDecimalSin[244] = -8987;
        noDecimalSin[245] = -9063;
        noDecimalSin[246] = -9135;
        noDecimalSin[247] = -9205;
        noDecimalSin[248] = -9271;
        noDecimalSin[249] = -9335;
        noDecimalSin[250] = -9396;
        noDecimalSin[251] = -9455;
        noDecimalSin[252] = -9510;
        noDecimalSin[253] = -9563;
        noDecimalSin[254] = -9612;
        noDecimalSin[255] = -9659;
        noDecimalSin[256] = -9702;
        noDecimalSin[257] = -9743;
        noDecimalSin[258] = -9781;
        noDecimalSin[259] = -9816;
        noDecimalSin[260] = -9848;
        noDecimalSin[261] = -9876;
        noDecimalSin[262] = -9902;
        noDecimalSin[263] = -9925;
        noDecimalSin[264] = -9945;
        noDecimalSin[265] = -9961;
        noDecimalSin[266] = -9975;
        noDecimalSin[267] = -9986;
        noDecimalSin[268] = -9993;
        noDecimalSin[269] = -9998;
        noDecimalSin[270] = -10000;
        noDecimalSin[271] = -9998;
        noDecimalSin[272] = -9993;
        noDecimalSin[273] = -9986;
        noDecimalSin[274] = -9975;
        noDecimalSin[275] = -9961;
        noDecimalSin[276] = -9945;
        noDecimalSin[277] = -9925;
        noDecimalSin[278] = -9902;
        noDecimalSin[279] = -9876;
        noDecimalSin[280] = -9848;
        noDecimalSin[281] = -9816;
        noDecimalSin[282] = -9781;
        noDecimalSin[283] = -9743;
        noDecimalSin[284] = -9702;
        noDecimalSin[285] = -9659;
        noDecimalSin[286] = -9612;
        noDecimalSin[287] = -9563;
        noDecimalSin[288] = -9510;
        noDecimalSin[289] = -9455;
        noDecimalSin[290] = -9396;
        noDecimalSin[291] = -9335;
        noDecimalSin[292] = -9271;
        noDecimalSin[293] = -9205;
        noDecimalSin[294] = -9135;
        noDecimalSin[295] = -9063;
        noDecimalSin[296] = -8987;
        noDecimalSin[297] = -8910;
        noDecimalSin[298] = -8829;
        noDecimalSin[299] = -8746;
        noDecimalSin[300] = -8660;
        noDecimalSin[301] = -8571;
        noDecimalSin[302] = -8480;
        noDecimalSin[303] = -8386;
        noDecimalSin[304] = -8290;
        noDecimalSin[305] = -8191;
        noDecimalSin[306] = -8090;
        noDecimalSin[307] = -7986;
        noDecimalSin[308] = -7880;
        noDecimalSin[309] = -7771;
        noDecimalSin[310] = -7660;
        noDecimalSin[311] = -7547;
        noDecimalSin[312] = -7431;
        noDecimalSin[313] = -7313;
        noDecimalSin[314] = -7193;
        noDecimalSin[315] = -7071;
        noDecimalSin[316] = -6946;
        noDecimalSin[317] = -6819;
        noDecimalSin[318] = -6691;
        noDecimalSin[319] = -6560;
        noDecimalSin[320] = -6427;
        noDecimalSin[321] = -6293;
        noDecimalSin[322] = -6156;
        noDecimalSin[323] = -6018;
        noDecimalSin[324] = -5877;
        noDecimalSin[325] = -5735;
        noDecimalSin[326] = -5591;
        noDecimalSin[327] = -5446;
        noDecimalSin[328] = -5299;
        noDecimalSin[329] = -5150;
        noDecimalSin[330] = -5000;
        noDecimalSin[331] = -4848;
        noDecimalSin[332] = -4694;
        noDecimalSin[333] = -4539;
        noDecimalSin[334] = -4383;
        noDecimalSin[335] = -4226;
        noDecimalSin[336] = -4067;
        noDecimalSin[337] = -3907;
        noDecimalSin[338] = -3746;
        noDecimalSin[339] = -3583;
        noDecimalSin[340] = -3420;
        noDecimalSin[341] = -3255;
        noDecimalSin[342] = -3090;
        noDecimalSin[343] = -2923;
        noDecimalSin[344] = -2756;
        noDecimalSin[345] = -2588;
        noDecimalSin[346] = -2419;
        noDecimalSin[347] = -2249;
        noDecimalSin[348] = -2079;
        noDecimalSin[349] = -1908;
        noDecimalSin[350] = -1736;
        noDecimalSin[351] = -1564;
        noDecimalSin[352] = -1391;
        noDecimalSin[353] = -1218;
        noDecimalSin[354] = -1045;
        noDecimalSin[355] = -871;
        noDecimalSin[356] = -697;
        noDecimalSin[357] = -523;
        noDecimalSin[358] = -348;
        noDecimalSin[359] = -174;

        noDecimalCos[0] = 10000;
        noDecimalCos[1] = 9998;
        noDecimalCos[2] = 9993;
        noDecimalCos[3] = 9986;
        noDecimalCos[4] = 9975;
        noDecimalCos[5] = 9961;
        noDecimalCos[6] = 9945;
        noDecimalCos[7] = 9925;
        noDecimalCos[8] = 9902;
        noDecimalCos[9] = 9876;
        noDecimalCos[10] = 9848;
        noDecimalCos[11] = 9816;
        noDecimalCos[12] = 9781;
        noDecimalCos[13] = 9743;
        noDecimalCos[14] = 9702;
        noDecimalCos[15] = 9659;
        noDecimalCos[16] = 9612;
        noDecimalCos[17] = 9563;
        noDecimalCos[18] = 9510;
        noDecimalCos[19] = 9455;
        noDecimalCos[20] = 9396;
        noDecimalCos[21] = 9335;
        noDecimalCos[22] = 9271;
        noDecimalCos[23] = 9205;
        noDecimalCos[24] = 9135;
        noDecimalCos[25] = 9063;
        noDecimalCos[26] = 8987;
        noDecimalCos[27] = 8910;
        noDecimalCos[28] = 8829;
        noDecimalCos[29] = 8746;
        noDecimalCos[30] = 8660;
        noDecimalCos[31] = 8571;
        noDecimalCos[32] = 8480;
        noDecimalCos[33] = 8386;
        noDecimalCos[34] = 8290;
        noDecimalCos[35] = 8191;
        noDecimalCos[36] = 8090;
        noDecimalCos[37] = 7986;
        noDecimalCos[38] = 7880;
        noDecimalCos[39] = 7771;
        noDecimalCos[40] = 7660;
        noDecimalCos[41] = 7547;
        noDecimalCos[42] = 7431;
        noDecimalCos[43] = 7313;
        noDecimalCos[44] = 7193;
        noDecimalCos[45] = 7071;
        noDecimalCos[46] = 6946;
        noDecimalCos[47] = 6819;
        noDecimalCos[48] = 6691;
        noDecimalCos[49] = 6560;
        noDecimalCos[50] = 6427;
        noDecimalCos[51] = 6293;
        noDecimalCos[52] = 6156;
        noDecimalCos[53] = 6018;
        noDecimalCos[54] = 5877;
        noDecimalCos[55] = 5735;
        noDecimalCos[56] = 5591;
        noDecimalCos[57] = 5446;
        noDecimalCos[58] = 5299;
        noDecimalCos[59] = 5150;
        noDecimalCos[60] = 5000;
        noDecimalCos[61] = 4848;
        noDecimalCos[62] = 4694;
        noDecimalCos[63] = 4539;
        noDecimalCos[64] = 4383;
        noDecimalCos[65] = 4226;
        noDecimalCos[66] = 4067;
        noDecimalCos[67] = 3907;
        noDecimalCos[68] = 3746;
        noDecimalCos[69] = 3583;
        noDecimalCos[70] = 3420;
        noDecimalCos[71] = 3255;
        noDecimalCos[72] = 3090;
        noDecimalCos[73] = 2923;
        noDecimalCos[74] = 2756;
        noDecimalCos[75] = 2588;
        noDecimalCos[76] = 2419;
        noDecimalCos[77] = 2249;
        noDecimalCos[78] = 2079;
        noDecimalCos[79] = 1908;
        noDecimalCos[80] = 1736;
        noDecimalCos[81] = 1564;
        noDecimalCos[82] = 1391;
        noDecimalCos[83] = 1218;
        noDecimalCos[84] = 1045;
        noDecimalCos[85] = 871;
        noDecimalCos[86] = 697;
        noDecimalCos[87] = 523;
        noDecimalCos[88] = 348;
        noDecimalCos[89] = 174;
        noDecimalCos[90] = 0;
        noDecimalCos[91] = -174;
        noDecimalCos[92] = -348;
        noDecimalCos[93] = -523;
        noDecimalCos[94] = -697;
        noDecimalCos[95] = -871;
        noDecimalCos[96] = -1045;
        noDecimalCos[97] = -1218;
        noDecimalCos[98] = -1391;
        noDecimalCos[99] = -1564;
        noDecimalCos[100] = -1736;
        noDecimalCos[101] = -1908;
        noDecimalCos[102] = -2079;
        noDecimalCos[103] = -2249;
        noDecimalCos[104] = -2419;
        noDecimalCos[105] = -2588;
        noDecimalCos[106] = -2756;
        noDecimalCos[107] = -2923;
        noDecimalCos[108] = -3090;
        noDecimalCos[109] = -3255;
        noDecimalCos[110] = -3420;
        noDecimalCos[111] = -3583;
        noDecimalCos[112] = -3746;
        noDecimalCos[113] = -3907;
        noDecimalCos[114] = -4067;
        noDecimalCos[115] = -4226;
        noDecimalCos[116] = -4383;
        noDecimalCos[117] = -4539;
        noDecimalCos[118] = -4694;
        noDecimalCos[119] = -4848;
        noDecimalCos[120] = -4999;
        noDecimalCos[121] = -5150;
        noDecimalCos[122] = -5299;
        noDecimalCos[123] = -5446;
        noDecimalCos[124] = -5591;
        noDecimalCos[125] = -5735;
        noDecimalCos[126] = -5877;
        noDecimalCos[127] = -6018;
        noDecimalCos[128] = -6156;
        noDecimalCos[129] = -6293;
        noDecimalCos[130] = -6427;
        noDecimalCos[131] = -6560;
        noDecimalCos[132] = -6691;
        noDecimalCos[133] = -6819;
        noDecimalCos[134] = -6946;
        noDecimalCos[135] = -7071;
        noDecimalCos[136] = -7193;
        noDecimalCos[137] = -7313;
        noDecimalCos[138] = -7431;
        noDecimalCos[139] = -7547;
        noDecimalCos[140] = -7660;
        noDecimalCos[141] = -7771;
        noDecimalCos[142] = -7880;
        noDecimalCos[143] = -7986;
        noDecimalCos[144] = -8090;
        noDecimalCos[145] = -8191;
        noDecimalCos[146] = -8290;
        noDecimalCos[147] = -8386;
        noDecimalCos[148] = -8480;
        noDecimalCos[149] = -8571;
        noDecimalCos[150] = -8660;
        noDecimalCos[151] = -8746;
        noDecimalCos[152] = -8829;
        noDecimalCos[153] = -8910;
        noDecimalCos[154] = -8987;
        noDecimalCos[155] = -9063;
        noDecimalCos[156] = -9135;
        noDecimalCos[157] = -9205;
        noDecimalCos[158] = -9271;
        noDecimalCos[159] = -9335;
        noDecimalCos[160] = -9396;
        noDecimalCos[161] = -9455;
        noDecimalCos[162] = -9510;
        noDecimalCos[163] = -9563;
        noDecimalCos[164] = -9612;
        noDecimalCos[165] = -9659;
        noDecimalCos[166] = -9702;
        noDecimalCos[167] = -9743;
        noDecimalCos[168] = -9781;
        noDecimalCos[169] = -9816;
        noDecimalCos[170] = -9848;
        noDecimalCos[171] = -9876;
        noDecimalCos[172] = -9902;
        noDecimalCos[173] = -9925;
        noDecimalCos[174] = -9945;
        noDecimalCos[175] = -9961;
        noDecimalCos[176] = -9975;
        noDecimalCos[177] = -9986;
        noDecimalCos[178] = -9993;
        noDecimalCos[179] = -9998;
        noDecimalCos[180] = -10000;
        noDecimalCos[181] = -9998;
        noDecimalCos[182] = -9993;
        noDecimalCos[183] = -9986;
        noDecimalCos[184] = -9975;
        noDecimalCos[185] = -9961;
        noDecimalCos[186] = -9945;
        noDecimalCos[187] = -9925;
        noDecimalCos[188] = -9902;
        noDecimalCos[189] = -9876;
        noDecimalCos[190] = -9848;
        noDecimalCos[191] = -9816;
        noDecimalCos[192] = -9781;
        noDecimalCos[193] = -9743;
        noDecimalCos[194] = -9702;
        noDecimalCos[195] = -9659;
        noDecimalCos[196] = -9612;
        noDecimalCos[197] = -9563;
        noDecimalCos[198] = -9510;
        noDecimalCos[199] = -9455;
        noDecimalCos[200] = -9396;
        noDecimalCos[201] = -9335;
        noDecimalCos[202] = -9271;
        noDecimalCos[203] = -9205;
        noDecimalCos[204] = -9135;
        noDecimalCos[205] = -9063;
        noDecimalCos[206] = -8987;
        noDecimalCos[207] = -8910;
        noDecimalCos[208] = -8829;
        noDecimalCos[209] = -8746;
        noDecimalCos[210] = -8660;
        noDecimalCos[211] = -8571;
        noDecimalCos[212] = -8480;
        noDecimalCos[213] = -8386;
        noDecimalCos[214] = -8290;
        noDecimalCos[215] = -8191;
        noDecimalCos[216] = -8090;
        noDecimalCos[217] = -7986;
        noDecimalCos[218] = -7880;
        noDecimalCos[219] = -7771;
        noDecimalCos[220] = -7660;
        noDecimalCos[221] = -7547;
        noDecimalCos[222] = -7431;
        noDecimalCos[223] = -7313;
        noDecimalCos[224] = -7193;
        noDecimalCos[225] = -7071;
        noDecimalCos[226] = -6946;
        noDecimalCos[227] = -6819;
        noDecimalCos[228] = -6691;
        noDecimalCos[229] = -6560;
        noDecimalCos[230] = -6427;
        noDecimalCos[231] = -6293;
        noDecimalCos[232] = -6156;
        noDecimalCos[233] = -6018;
        noDecimalCos[234] = -5877;
        noDecimalCos[235] = -5735;
        noDecimalCos[236] = -5591;
        noDecimalCos[237] = -5446;
        noDecimalCos[238] = -5299;
        noDecimalCos[239] = -5150;
        noDecimalCos[240] = -5000;
        noDecimalCos[241] = -4848;
        noDecimalCos[242] = -4694;
        noDecimalCos[243] = -4539;
        noDecimalCos[244] = -4383;
        noDecimalCos[245] = -4226;
        noDecimalCos[246] = -4067;
        noDecimalCos[247] = -3907;
        noDecimalCos[248] = -3746;
        noDecimalCos[249] = -3583;
        noDecimalCos[250] = -3420;
        noDecimalCos[251] = -3255;
        noDecimalCos[252] = -3090;
        noDecimalCos[253] = -2923;
        noDecimalCos[254] = -2756;
        noDecimalCos[255] = -2588;
        noDecimalCos[256] = -2419;
        noDecimalCos[257] = -2249;
        noDecimalCos[258] = -2079;
        noDecimalCos[259] = -1908;
        noDecimalCos[260] = -1736;
        noDecimalCos[261] = -1564;
        noDecimalCos[262] = -1391;
        noDecimalCos[263] = -1218;
        noDecimalCos[264] = -1045;
        noDecimalCos[265] = -871;
        noDecimalCos[266] = -697;
        noDecimalCos[267] = -523;
        noDecimalCos[268] = -348;
        noDecimalCos[269] = -174;
        noDecimalCos[270] = 0;
        noDecimalCos[271] = 174;
        noDecimalCos[272] = 348;
        noDecimalCos[273] = 523;
        noDecimalCos[274] = 697;
        noDecimalCos[275] = 871;
        noDecimalCos[276] = 1045;
        noDecimalCos[277] = 1218;
        noDecimalCos[278] = 1391;
        noDecimalCos[279] = 1564;
        noDecimalCos[280] = 1736;
        noDecimalCos[281] = 1908;
        noDecimalCos[282] = 2079;
        noDecimalCos[283] = 2249;
        noDecimalCos[284] = 2419;
        noDecimalCos[285] = 2588;
        noDecimalCos[286] = 2756;
        noDecimalCos[287] = 2923;
        noDecimalCos[288] = 3090;
        noDecimalCos[289] = 3255;
        noDecimalCos[290] = 3420;
        noDecimalCos[291] = 3583;
        noDecimalCos[292] = 3746;
        noDecimalCos[293] = 3907;
        noDecimalCos[294] = 4067;
        noDecimalCos[295] = 4226;
        noDecimalCos[296] = 4383;
        noDecimalCos[297] = 4539;
        noDecimalCos[298] = 4694;
        noDecimalCos[299] = 4848;
        noDecimalCos[300] = 5000;
        noDecimalCos[301] = 5150;
        noDecimalCos[302] = 5299;
        noDecimalCos[303] = 5446;
        noDecimalCos[304] = 5591;
        noDecimalCos[305] = 5735;
        noDecimalCos[306] = 5877;
        noDecimalCos[307] = 6018;
        noDecimalCos[308] = 6156;
        noDecimalCos[309] = 6293;
        noDecimalCos[310] = 6427;
        noDecimalCos[311] = 6560;
        noDecimalCos[312] = 6691;
        noDecimalCos[313] = 6819;
        noDecimalCos[314] = 6946;
        noDecimalCos[315] = 7071;
        noDecimalCos[316] = 7193;
        noDecimalCos[317] = 7313;
        noDecimalCos[318] = 7431;
        noDecimalCos[319] = 7547;
        noDecimalCos[320] = 7660;
        noDecimalCos[321] = 7771;
        noDecimalCos[322] = 7880;
        noDecimalCos[323] = 7986;
        noDecimalCos[324] = 8090;
        noDecimalCos[325] = 8191;
        noDecimalCos[326] = 8290;
        noDecimalCos[327] = 8386;
        noDecimalCos[328] = 8480;
        noDecimalCos[329] = 8571;
        noDecimalCos[330] = 8660;
        noDecimalCos[331] = 8746;
        noDecimalCos[332] = 8829;
        noDecimalCos[333] = 8910;
        noDecimalCos[334] = 8987;
        noDecimalCos[335] = 9063;
        noDecimalCos[336] = 9135;
        noDecimalCos[337] = 9205;
        noDecimalCos[338] = 9271;
        noDecimalCos[339] = 9335;
        noDecimalCos[340] = 9396;
        noDecimalCos[341] = 9455;
        noDecimalCos[342] = 9510;
        noDecimalCos[343] = 9563;
        noDecimalCos[344] = 9612;
        noDecimalCos[345] = 9659;
        noDecimalCos[346] = 9702;
        noDecimalCos[347] = 9743;
        noDecimalCos[348] = 9781;
        noDecimalCos[349] = 9816;
        noDecimalCos[350] = 9848;
        noDecimalCos[351] = 9876;
        noDecimalCos[352] = 9902;
        noDecimalCos[353] = 9925;
        noDecimalCos[354] = 9945;
        noDecimalCos[355] = 9961;
        noDecimalCos[356] = 9975;
        noDecimalCos[357] = 9986;
        noDecimalCos[358] = 9993;
        noDecimalCos[359] = 9998;

        noDecimalTan[0] = 0;
        noDecimalTan[1] = 174;
        noDecimalTan[2] = 349;
        noDecimalTan[3] = 524;
        noDecimalTan[4] = 699;
        noDecimalTan[5] = 874;
        noDecimalTan[6] = 1051;
        noDecimalTan[7] = 1227;
        noDecimalTan[8] = 1405;
        noDecimalTan[9] = 1583;
        noDecimalTan[10] = 1763;
        noDecimalTan[11] = 1943;
        noDecimalTan[12] = 2125;
        noDecimalTan[13] = 2308;
        noDecimalTan[14] = 2493;
        noDecimalTan[15] = 2679;
        noDecimalTan[16] = 2867;
        noDecimalTan[17] = 3057;
        noDecimalTan[18] = 3249;
        noDecimalTan[19] = 3443;
        noDecimalTan[20] = 3639;
        noDecimalTan[21] = 3838;
        noDecimalTan[22] = 4040;
        noDecimalTan[23] = 4244;
        noDecimalTan[24] = 4452;
        noDecimalTan[25] = 4663;
        noDecimalTan[26] = 4877;
        noDecimalTan[27] = 5095;
        noDecimalTan[28] = 5317;
        noDecimalTan[29] = 5543;
        noDecimalTan[30] = 5773;
        noDecimalTan[31] = 6008;
        noDecimalTan[32] = 6248;
        noDecimalTan[33] = 6494;
        noDecimalTan[34] = 6745;
        noDecimalTan[35] = 7002;
        noDecimalTan[36] = 7265;
        noDecimalTan[37] = 7535;
        noDecimalTan[38] = 7812;
        noDecimalTan[39] = 8097;
        noDecimalTan[40] = 8390;
        noDecimalTan[41] = 8692;
        noDecimalTan[42] = 9004;
        noDecimalTan[43] = 9325;
        noDecimalTan[44] = 9656;
        noDecimalTan[45] = 9999;

        noDecimalTan[46] = 10355;
        noDecimalTan[47] = 10723;
        noDecimalTan[48] = 11106;
        noDecimalTan[49] = 11503;
        noDecimalTan[50] = 11917;
        noDecimalTan[51] = 12348;
        noDecimalTan[52] = 12799;
        noDecimalTan[53] = 13270;
        noDecimalTan[54] = 13763;
        noDecimalTan[55] = 14281;
        noDecimalTan[56] = 14825;
        noDecimalTan[57] = 15398;
        noDecimalTan[58] = 16003;
        noDecimalTan[59] = 16642;
        noDecimalTan[60] = 17320;
        noDecimalTan[61] = 18040;
        noDecimalTan[62] = 18807;
        noDecimalTan[63] = 19626;
        noDecimalTan[64] = 20503;
        noDecimalTan[65] = 21445;
        noDecimalTan[66] = 22460;
        noDecimalTan[67] = 23558;
        noDecimalTan[68] = 24750;
        noDecimalTan[69] = 26050;
        noDecimalTan[70] = 27474;
        noDecimalTan[71] = 29042;
        noDecimalTan[72] = 30776;
        noDecimalTan[73] = 32708;
        noDecimalTan[74] = 34874;
        noDecimalTan[75] = 37320;
        noDecimalTan[76] = 40107;
        noDecimalTan[77] = 43314;
        noDecimalTan[78] = 47046;
        noDecimalTan[79] = 51445;
        noDecimalTan[80] = 56712;
        noDecimalTan[81] = 63137;
        noDecimalTan[82] = 71153;
        noDecimalTan[83] = 81443;
        noDecimalTan[84] = 95143;
        noDecimalTan[85] = 114300;
        noDecimalTan[86] = 143006;
        noDecimalTan[87] = 190811;
        noDecimalTan[88] = 286362;
        noDecimalTan[89] = 572899;
        noDecimalTan[90] = Integer.MAX_VALUE;
        noDecimalTan[91] = -572899;
        noDecimalTan[92] = -286362;
        noDecimalTan[93] = -190811;
        noDecimalTan[94] = -143006;
        noDecimalTan[95] = -114300;
        noDecimalTan[96] = -95143;
        noDecimalTan[97] = -81443;
        noDecimalTan[98] = -71153;
        noDecimalTan[99] = -63137;
        noDecimalTan[100] = -56712;
        noDecimalTan[101] = -51445;
        noDecimalTan[102] = -47046;
        noDecimalTan[103] = -43314;
        noDecimalTan[104] = -40107;
        noDecimalTan[105] = -37320;
        noDecimalTan[106] = -34874;
        noDecimalTan[107] = -32708;
        noDecimalTan[108] = -30776;
        noDecimalTan[109] = -29042;
        noDecimalTan[110] = -27474;
        noDecimalTan[111] = -26050;
        noDecimalTan[112] = -24750;
        noDecimalTan[113] = -23558;
        noDecimalTan[114] = -22460;
        noDecimalTan[115] = -21445;
        noDecimalTan[116] = -20503;
        noDecimalTan[117] = -19626;
        noDecimalTan[118] = -18807;
        noDecimalTan[119] = -18040;
        noDecimalTan[120] = -17320;
        noDecimalTan[121] = -16642;
        noDecimalTan[122] = -16003;
        noDecimalTan[123] = -15398;
        noDecimalTan[124] = -14825;
        noDecimalTan[125] = -14281;
        noDecimalTan[126] = -13763;
        noDecimalTan[127] = -13270;
        noDecimalTan[128] = -12799;
        noDecimalTan[129] = -12348;
        noDecimalTan[130] = -11917;
        noDecimalTan[131] = -11503;
        noDecimalTan[132] = -11106;
        noDecimalTan[133] = -10723;
        noDecimalTan[134] = -10355;

        noDecimalTan[135] = -10000;
        noDecimalTan[136] = -9656;
        noDecimalTan[137] = -9325;
        noDecimalTan[138] = -9004;
        noDecimalTan[139] = -8692;
        noDecimalTan[140] = -8390;
        noDecimalTan[141] = -8097;
        noDecimalTan[142] = -7812;
        noDecimalTan[143] = -7535;
        noDecimalTan[144] = -7265;
        noDecimalTan[145] = -7002;
        noDecimalTan[146] = -6745;
        noDecimalTan[147] = -6494;
        noDecimalTan[148] = -6248;
        noDecimalTan[149] = -6008;
        noDecimalTan[150] = -5773;
        noDecimalTan[151] = -5543;
        noDecimalTan[152] = -5317;
        noDecimalTan[153] = -5095;
        noDecimalTan[154] = -4877;
        noDecimalTan[155] = -4663;
        noDecimalTan[156] = -4452;
        noDecimalTan[157] = -4244;
        noDecimalTan[158] = -4040;
        noDecimalTan[159] = -3838;
        noDecimalTan[160] = -3639;
        noDecimalTan[161] = -3443;
        noDecimalTan[162] = -3249;
        noDecimalTan[163] = -3057;
        noDecimalTan[164] = -2867;
        noDecimalTan[165] = -2679;
        noDecimalTan[166] = -2493;
        noDecimalTan[167] = -2308;
        noDecimalTan[168] = -2125;
        noDecimalTan[169] = -1943;
        noDecimalTan[170] = -1763;
        noDecimalTan[171] = -1583;
        noDecimalTan[172] = -1405;
        noDecimalTan[173] = -1227;
        noDecimalTan[174] = -1051;
        noDecimalTan[175] = -874;
        noDecimalTan[176] = -699;
        noDecimalTan[177] = -524;
        noDecimalTan[178] = -349;
        noDecimalTan[179] = -174;
        noDecimalTan[180] = 0;
        noDecimalTan[181] = 174;
        noDecimalTan[182] = 349;
        noDecimalTan[183] = 524;
        noDecimalTan[184] = 699;
        noDecimalTan[185] = 874;
        noDecimalTan[186] = 1051;
        noDecimalTan[187] = 1227;
        noDecimalTan[188] = 1405;
        noDecimalTan[189] = 1583;
        noDecimalTan[190] = 1763;
        noDecimalTan[191] = 1943;
        noDecimalTan[192] = 2125;
        noDecimalTan[193] = 2308;
        noDecimalTan[194] = 2493;
        noDecimalTan[195] = 2679;
        noDecimalTan[196] = 2867;
        noDecimalTan[197] = 3057;
        noDecimalTan[198] = 3249;
        noDecimalTan[199] = 3443;
        noDecimalTan[200] = 3639;
        noDecimalTan[201] = 3838;
        noDecimalTan[202] = 4040;
        noDecimalTan[203] = 4244;
        noDecimalTan[204] = 4452;
        noDecimalTan[205] = 4663;
        noDecimalTan[206] = 4877;
        noDecimalTan[207] = 5095;
        noDecimalTan[208] = 5317;
        noDecimalTan[209] = 5543;
        noDecimalTan[210] = 5773;
        noDecimalTan[211] = 6008;
        noDecimalTan[212] = 6248;
        noDecimalTan[213] = 6494;
        noDecimalTan[214] = 6745;
        noDecimalTan[215] = 7002;
        noDecimalTan[216] = 7265;
        noDecimalTan[217] = 7535;
        noDecimalTan[218] = 7812;
        noDecimalTan[219] = 8097;
        noDecimalTan[220] = 8390;
        noDecimalTan[221] = 8692;
        noDecimalTan[222] = 9004;
        noDecimalTan[223] = 9325;
        noDecimalTan[224] = 9656;
        noDecimalTan[225] = 9999;

        noDecimalTan[226] = 10355;
        noDecimalTan[227] = 10723;
        noDecimalTan[228] = 11106;
        noDecimalTan[229] = 11503;
        noDecimalTan[230] = 11917;
        noDecimalTan[231] = 12348;
        noDecimalTan[232] = 12799;
        noDecimalTan[233] = 13270;
        noDecimalTan[234] = 13763;
        noDecimalTan[235] = 14281;
        noDecimalTan[236] = 14825;
        noDecimalTan[237] = 15398;
        noDecimalTan[238] = 16003;
        noDecimalTan[239] = 16642;
        noDecimalTan[240] = 17320;
        noDecimalTan[241] = 18040;
        noDecimalTan[242] = 18807;
        noDecimalTan[243] = 19626;
        noDecimalTan[244] = 20503;
        noDecimalTan[245] = 21445;
        noDecimalTan[246] = 22460;
        noDecimalTan[247] = 23558;
        noDecimalTan[248] = 24750;
        noDecimalTan[249] = 26050;
        noDecimalTan[250] = 27474;
        noDecimalTan[251] = 29042;
        noDecimalTan[252] = 30776;
        noDecimalTan[253] = 32708;
        noDecimalTan[254] = 34874;
        noDecimalTan[255] = 37320;
        noDecimalTan[256] = 40107;
        noDecimalTan[257] = 43314;
        noDecimalTan[258] = 47046;
        noDecimalTan[259] = 51445;
        noDecimalTan[260] = 56712;
        noDecimalTan[261] = 63137;
        noDecimalTan[262] = 71153;
        noDecimalTan[263] = 81443;
        noDecimalTan[264] = 95143;
        noDecimalTan[265] = 114300;
        noDecimalTan[266] = 143006;
        noDecimalTan[267] = 190811;
        noDecimalTan[268] = 286362;
        noDecimalTan[269] = 572899;
        noDecimalTan[270] = Integer.MAX_VALUE;
        noDecimalTan[271] = -572899;
        noDecimalTan[272] = -286362;
        noDecimalTan[273] = -190811;
        noDecimalTan[274] = -143006;
        noDecimalTan[275] = -114300;
        noDecimalTan[276] = -95143;
        noDecimalTan[277] = -81443;
        noDecimalTan[278] = -71153;
        noDecimalTan[279] = -63137;
        noDecimalTan[280] = -56712;
        noDecimalTan[281] = -51445;
        noDecimalTan[282] = -47046;
        noDecimalTan[283] = -43314;
        noDecimalTan[284] = -40107;
        noDecimalTan[285] = -37320;
        noDecimalTan[286] = -34874;
        noDecimalTan[287] = -32708;
        noDecimalTan[288] = -30776;
        noDecimalTan[289] = -29042;
        noDecimalTan[290] = -27474;
        noDecimalTan[291] = -26050;
        noDecimalTan[292] = -24750;
        noDecimalTan[293] = -23558;
        noDecimalTan[294] = -22460;
        noDecimalTan[295] = -21445;
        noDecimalTan[296] = -20503;
        noDecimalTan[297] = -19626;
        noDecimalTan[298] = -18807;
        noDecimalTan[299] = -18040;
        noDecimalTan[300] = -17320;
        noDecimalTan[301] = -16642;
        noDecimalTan[302] = -16003;
        noDecimalTan[303] = -15398;
        noDecimalTan[304] = -14825;
        noDecimalTan[305] = -14281;
        noDecimalTan[306] = -13763;
        noDecimalTan[307] = -13270;
        noDecimalTan[308] = -12799;
        noDecimalTan[309] = -12348;
        noDecimalTan[310] = -11917;
        noDecimalTan[311] = -11503;
        noDecimalTan[312] = -11106;
        noDecimalTan[313] = -10723;
        noDecimalTan[314] = -10355;

        noDecimalTan[315] = -10000;
        noDecimalTan[316] = -9656;
        noDecimalTan[317] = -9325;
        noDecimalTan[318] = -9004;
        noDecimalTan[319] = -8692;
        noDecimalTan[320] = -8390;
        noDecimalTan[321] = -8097;
        noDecimalTan[322] = -7812;
        noDecimalTan[323] = -7535;
        noDecimalTan[324] = -7265;
        noDecimalTan[325] = -7002;
        noDecimalTan[326] = -6745;
        noDecimalTan[327] = -6494;
        noDecimalTan[328] = -6248;
        noDecimalTan[329] = -6008;
        noDecimalTan[330] = -5773;
        noDecimalTan[331] = -5543;
        noDecimalTan[332] = -5317;
        noDecimalTan[333] = -5095;
        noDecimalTan[334] = -4877;
        noDecimalTan[335] = -4663;
        noDecimalTan[336] = -4452;
        noDecimalTan[337] = -4244;
        noDecimalTan[338] = -4040;
        noDecimalTan[339] = -3838;
        noDecimalTan[340] = -3639;
        noDecimalTan[341] = -3443;
        noDecimalTan[342] = -3249;
        noDecimalTan[343] = -3057;
        noDecimalTan[344] = -2867;
        noDecimalTan[345] = -2679;
        noDecimalTan[346] = -2493;
        noDecimalTan[347] = -2308;
        noDecimalTan[348] = -2125;
        noDecimalTan[349] = -1943;
        noDecimalTan[350] = -1763;
        noDecimalTan[351] = -1583;
        noDecimalTan[352] = -1405;
        noDecimalTan[353] = -1227;
        noDecimalTan[354] = -1051;
        noDecimalTan[355] = -874;
        noDecimalTan[356] = -699;
        noDecimalTan[357] = -524;
        noDecimalTan[358] = -349;
        noDecimalTan[359] = -174;
        noDecimalTan[0] = -174;
    }

    public long sin(short angle)
    {
        return noDecimalSin[angle];
    }

    public long cos(short angle)
    {
        return noDecimalCos[angle];
    }

    public long tan(short angle)
    {
        return noDecimalTan[angle];
    }
    
    public int SCALE = 10000;
    
    //private final String ANTITAN = "antiTan";
    
    public short antiTan(int dx, int dy)
       throws Exception
    {
        long ratio = Integer.MAX_VALUE - 1;
        if (dy != 0)
        {
        	long dxl = dx;
            ratio = SCALE * dxl / dy;

            //Major bug in Android?
            if(dx <= 0 && dy < 0)
            {
            	//PreLogUtil.put("How is dx or dy not negative? " + PositionStrings + dx + PositionStrings + dy, this, ANTITAN);
            	ratio = mathUtil.abs(ratio);
            }

            //logUtil.put("ratioUnscaled: " + ratioUnscaled, this, ANTITAN);
        }

        if ((dx >= 0 && dy < 0))
        {
            for (short index = 180; index > 90; index--)
            {
                if (ratio <= noDecimalTan[index])
                {
                    if(ratio > noDecimalTan[index - 1])
                    {
                        //logUtil.put(Integer.toString(result), this, ANTITAN))
                        return index;
                    }
                    else
                        if(noDecimalTan[index - 1] == Integer.MAX_VALUE && ratio > Integer.MIN_VALUE)
                        {
                            return index;
                        }
                }
            }
        }
        else
        if ((dx < 0 && dy <= 0))
        {
            for (short index = 270; index > 180; index--)
            {
                if (ratio < noDecimalTan[index] && ratio >= noDecimalTan[index - 1])
                {
                    //logUtil.put(Integer.toString(result), this, ANTITAN))
                    return index;
                }
            }
        }
        else
        if ((dx < 0 && dy > 0))
        {
            for (short index = (short) (noDecimalTan.length - 1); index > 270; index--)
            {
                if (ratio < noDecimalTan[index])
                {
                    if(ratio >= noDecimalTan[index - 1])
                    {
                        //logUtil.put(Integer.toString(result), this, ANTITAN))
                        return index;
                    }
                    else
                        if(noDecimalTan[index - 1] == Integer.MAX_VALUE && ratio >= Integer.MIN_VALUE)
                        {
                            return index;
                        }
                }
            }
        }
        else
        if ((dx >= 0 && dy >= 0))
        {
            for (short index = 90; index > 0; index--)
            {
                if (ratio < noDecimalTan[index] && ratio >= noDecimalTan[index - 1])
                {
                    //logUtil.put(Integer.toString(result), this, ANTITAN);
                    return index;
                }
            }
        }

        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Invalid Ratio: ");
        stringBuffer.append(ratio);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(PositionStrings.getInstance().DX_LABEL);
        stringBuffer.append(dx);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(PositionStrings.getInstance().DX_LABEL);
        stringBuffer.append(dy);
        
        throw new Exception(stringBuffer.toString());
    }
    
    //Used to help figure out the correct quadrants when +- values are mixed up
    public short antiTanDebug(int screenX, int screenY, int targetX, int targetY)
    throws Exception
    {
		final String METHOD_NAME = "antiTanDebug";
		final String TARGET_ANGLE = " Targeting angle: ";

                final StringMaker stringMaker = new StringMaker();
                
		PreLogUtil.put(stringMaker.append("screenX: ").append(screenX).append(" screenY: ").append(screenY).append(" targetX: ").append(targetX).append(" targetY: ").append(targetY).toString(), this, METHOD_NAME);
		
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

		for(int index = 0; index < dx.length; index++)
		{
			for(int index2 = 0; index2 < dy.length; index2++)
			{
				angleOfTarget = this.antiTan(dx[index], dy[index2]);

				Angle angle = AngleFactory.getInstance().getInstance(angleOfTarget);
                                stringMaker.delete(0, stringMaker.length());
				PreLogUtil.put(stringMaker.append(CommonLabels.getInstance().INDEX_LABEL).append(index).append(" index2: ").append(index2).append(PositionStrings.getInstance().DX_LABEL).append(dx[index]).append(PositionStrings.getInstance().DY_LABEL).append(dy[index2]).append(TARGET_ANGLE).append(angleOfTarget).append(CommonSeps.getInstance().EQUALS).append(angle.getValue()).toString(), this, METHOD_NAME);
			}
		}

		angleOfTarget = this.antiTan(dx[0], dy[0]);

		return angleOfTarget;
    }
}
