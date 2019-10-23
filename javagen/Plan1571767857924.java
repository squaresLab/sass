public class Plan1571767857924 extends Plan { 
public static void main(String[] args) { 

for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
StartServer("B");

}

DecreaseTraffic("A");

}



}
}
