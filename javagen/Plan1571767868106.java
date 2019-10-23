public class Plan1571767868106 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");

DecreaseTraffic("A");

StartServer("B");

} else {
StartServer("B");
}

}

}
}
