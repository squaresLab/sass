library(readr)
library(ggplot2)

dat <- read_csv("/home/ckinneer/research/analysis-code/data.csv")

dat$generation <- as.factor(dat$generation)

p <- ggplot(data=dat, aes(x=generation,y=profit))
p <- p + geom_boxplot()
p + facet_wrap(~ timestep)

p <- p + ylab("Diversity\n(average pairwise tree edit distance)") + xlab("Generation") + scale_fill_discrete(name="Starting Plan")  + theme_bw()
p <- p + theme(text=element_text(size=10), title=element_text(size=30,face="bold"),legend.title=element_text(size=30,face="bold"),legend.text=element_text(size=25),legend.key.size=unit(0.75,"in"))
p + facet_wrap(~ scenario) + scale_color_manual(values=cbPalette,name="Starting Plan")