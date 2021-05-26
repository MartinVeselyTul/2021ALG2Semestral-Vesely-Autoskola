# 2021ALG2Semestral-Vesely-Autoskola
<h3> Zádání práce </h3>
Program autoškola bude zpracovávat výsledky autoškoly po teoretických testech a závěrečných zkouškách. Vstupem jsou 2 textové soubory - soubor všech účastníků s počtem bodů z teoretického testu a soubor, zda zvládli závěrečné jízdy (účastníci s malým počtem bodů zde nejsou). První soubor obsahuje jméno, přijímení, pohlaví, počet bodů a datum narození. Druhý soubor obsahuje jméno, přijímení a boolean, zda zvládli závěrečné zkoušky. Data v souborech jsou odděleny čárkou. 
Program umožňuje uživateli zobrazit, jací žáci udělali písemné testy, jací udělali kompletní autoškoly, jací neudělali závěrečné testy, nebo jací nesplnili ani jedno. Následně program umožní filtrování dle pohlaví, nebo setřídění dle počtu bodů, nebo třídění dle křestního jména. Následně se program ptá uživatele, zda chce vybraný soubor uložit, případně do jakého formátu (formát pdf přes externí knihovnu zatím nefunguje kvůli java.util.List, které není kompatibilní s druhou knihovnou). Po uložení může uživatel spustit program znovu.
<h3> Návrh řešení </h3>
<h4> Funkční specifikace </h4>
Menu -> výběr jaké účastníky chce uživatel zobrazit
<p>1. seznam všech účastníků </p>
<p>2. seznam účastníků, kteří udělali autoškolu</p>
<p>3. seznam účastníků, kteří neudělali teoretické testy</p>
<p>4. seznam účastníků, kteří neudělali závěrečné testy</p>
Filtrovací menu -> jak chce uživatel setřídit/filtrovat zvolený seznam
- dle pohlaví, dle počtu bodů, dle data narození, dle jména
<h4> Popis struktury vstupních a výstupních souborů </h4>
