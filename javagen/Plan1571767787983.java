public class Plan1571767787983 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}

DecreaseTraffic("A");

}

StartServer("C");


}
}
