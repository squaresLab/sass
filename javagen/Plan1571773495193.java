public class Plan1571773495193 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("A");
}

if ( StartServer("B") ) {
StartServer("A");
} else {
IncreaseTraffic("B");
}


}
}
