public class Plan1571770152852 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("B");
}

}

}

StartServer("C");
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}




for (int i = 0; i < 4 ; i++) {
StartServer("B");
}


}
}
